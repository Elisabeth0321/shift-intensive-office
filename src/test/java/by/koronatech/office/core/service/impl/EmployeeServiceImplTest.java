package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.entity.Department;
import by.koronatech.office.entity.Employee;
import by.koronatech.office.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private DepartmentService departmentService;

    private Employee employee;
    private EmployeeDTO employeeDTO;
    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        department = new Department();
        department.setId(1L);
        department.setName("PR-отдел");

        employee = new Employee();
        employee.setId(1L);
        employee.setName("Вася Пупкин");
        employee.setDepartment(department);
        employee.setIsManager(false);

        employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Вася Пупкин");
        employeeDTO.setDepartmentName("PR-отдел");
    }

    @Test
    void addEmployee_ReturnsSavedEmployee() {
        when(employeeMapper.toEntity(any(EmployeeDTO.class))).thenReturn(employee);
        when(departmentService.findByName("PR-отдел")).thenReturn(department);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.toDto(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeService.add(employeeDTO);

        assertNotNull(result);
        assertEquals("Вася Пупкин", result.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void getByDepartment_ReturnsPagedEmployees() {
        Page<Employee> employeePage = new PageImpl<>(List.of(employee));
        when(employeeRepository.findByDepartmentId(eq(1L), any(Pageable.class))).thenReturn(employeePage);
        when(employeeMapper.toDto(any(Employee.class))).thenReturn(employeeDTO);

        Page<EmployeeDTO> result = employeeService.getByDepartment(1L, Pageable.unpaged());

        assertEquals(1, result.getTotalElements());
        assertEquals("Вася Пупкин", result.getContent().get(0).getName());
    }

    @Test
    void promoteToManager_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.toDto(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeService.promoteToManager(1L);

        assertNotNull(result);
        assertTrue(employee.getIsManager());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void promoteToManager_AlreadyManager_ThrowsException() {
        employee.setIsManager(true);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> employeeService.promoteToManager(1L));

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

    @Test
    void updateEmployee_ReturnsUpdatedEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        doAnswer(invocation -> {
            Employee emp = invocation.getArgument(0);
            EmployeeDTO dto = invocation.getArgument(1);
            emp.setName(dto.getName());
            return null;
        }).when(employeeMapper).merge(any(Employee.class), any(EmployeeDTO.class));

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.toDto(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeService.update(1L, employeeDTO);

        assertNotNull(result);
        assertEquals("Вася Пупкин", result.getName());
        verify(employeeRepository, times(1)).save(employee);
    }


    @Test
    void deleteEmployee_Success() {
        doNothing().when(employeeRepository).deleteById(1L);

        assertDoesNotThrow(() -> employeeService.delete(1L));

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
