package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.entity.Department;
import by.koronatech.office.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .departmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .salary(employee.getSalary())
                .isManager(employee.getIsManager())
                .build();
    }

    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setIsManager(dto.getIsManager());

        if (dto.getDepartmentName() != null) {
            employee.setDepartment(new Department());
            employee.getDepartment().setName(dto.getDepartmentName());
        }

        return employee;
    }

    public void merge(Employee employee, EmployeeDTO dto) {
        if (dto == null || employee == null) {
            return;
        }

        if (dto.getName() != null) {
            employee.setName(dto.getName());
        }

        if (dto.getSalary() != null) {
            employee.setSalary(dto.getSalary());
        }

        if (dto.getIsManager() != null) {
            employee.setIsManager(dto.getIsManager());
        }

        if (dto.getDepartmentName() != null) {
            if (employee.getDepartment() == null) {
                employee.setDepartment(new Department());
            }
            employee.getDepartment().setName(dto.getDepartmentName());
        }
    }
}
