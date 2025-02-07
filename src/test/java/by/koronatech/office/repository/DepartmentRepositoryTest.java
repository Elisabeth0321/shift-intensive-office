package by.koronatech.office.repository;

import by.koronatech.office.entity.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentRepositoryTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void testFindByName() {
        Department department = new Department();
        department.setId(1L);
        department.setName("IT-отдел");

        when(departmentRepository.findByName(eq("IT-отдел"))).thenReturn(Optional.of(department));

        Optional<Department> result = departmentRepository.findByName("IT-отдел");

        assertEquals(true, result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("IT-отдел", result.get().getName());
    }
}
