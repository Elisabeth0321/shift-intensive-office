package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDto(Department department) {
        if (department == null) {
            return null;
        }

        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}