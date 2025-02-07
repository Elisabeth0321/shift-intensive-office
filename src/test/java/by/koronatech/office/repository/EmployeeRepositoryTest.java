package by.koronatech.office.repository;

import by.koronatech.office.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void testFindByDepartmentId() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Вася Пупкин");

        Page<Employee> page = new PageImpl<>(Collections.singletonList(employee));

        when(employeeRepository.findByDepartmentId(eq(1L), any(Pageable.class))).thenReturn(page);

        Page<Employee> result = employeeRepository.findByDepartmentId(1L, Pageable.unpaged());

        assertEquals(1, result.getTotalElements());
        assertEquals("Вася Пупкин", result.getContent().get(0).getName());
    }
}