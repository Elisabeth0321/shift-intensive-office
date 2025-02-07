package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.core.mapper.DepartmentMapper;
import by.koronatech.office.core.service.exception.NotFoundException;
import by.koronatech.office.entity.Department;
import by.koronatech.office.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;
    private DepartmentDTO departmentDTO;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("IT");

        departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("IT");
    }

    @Test
    void getAllDepartments_ShouldReturnPagedDepartments() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Department> departments = List.of(department);
        Page<Department> departmentPage = new PageImpl<>(departments);

        when(departmentRepository.findAll(pageable)).thenReturn(departmentPage);
        when(departmentMapper.toDto(department)).thenReturn(departmentDTO);

        Page<DepartmentDTO> result = departmentService.getAllDepartments(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("IT", result.getContent().get(0).getName());

        verify(departmentRepository, times(1)).findAll(pageable);
    }

    @Test
    void getDepartmentIdByName_ShouldReturnId_WhenDepartmentExists() {
        when(departmentRepository.findByName("IT")).thenReturn(Optional.of(department));

        Long departmentId = departmentService.getDepartmentIdByName("IT");

        assertNotNull(departmentId);
        assertEquals(1L, departmentId);
        verify(departmentRepository, times(1)).findByName("IT");
    }

    @Test
    void getDepartmentIdByName_ShouldReturnNull_WhenDepartmentDoesNotExist() {
        when(departmentRepository.findByName("HR")).thenReturn(Optional.empty());

        Long departmentId = departmentService.getDepartmentIdByName("HR");

        assertNull(departmentId);
        verify(departmentRepository, times(1)).findByName("HR");
    }

    @Test
    void findById_ShouldReturnDepartment_WhenExists() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department foundDepartment = departmentService.findById(1L);

        assertNotNull(foundDepartment);
        assertEquals("IT", foundDepartment.getName());
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(departmentRepository.findById(2L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            departmentService.findById(2L);
        });

        assertEquals("Департамент с ID 2 не найден", exception.getMessage());
        verify(departmentRepository, times(1)).findById(2L);
    }

    @Test
    void findByName_ShouldReturnDepartment_WhenExists() {
        when(departmentRepository.findByName("IT")).thenReturn(Optional.of(department));

        Department foundDepartment = departmentService.findByName("IT");

        assertNotNull(foundDepartment);
        assertEquals("IT", foundDepartment.getName());
        verify(departmentRepository, times(1)).findByName("IT");
    }

    @Test
    void findByName_ShouldThrowException_WhenNotFound() {
        when(departmentRepository.findByName("Finance")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            departmentService.findByName("Finance");
        });

        assertEquals("Департамент Finance не найден", exception.getMessage());
        verify(departmentRepository, times(1)).findByName("Finance");
    }

    @Test
    void shouldReturnDepartmentById() {
        Department department = new Department(1L, "HR");
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.findById(1L);

        assertThat(result).isEqualTo(department);
        verify(departmentRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDepartmentNotFound() {
        when(departmentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.findById(99L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Департамент с ID 99 не найден");

        verify(departmentRepository).findById(99L);
    }
}
