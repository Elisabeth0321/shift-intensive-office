package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapper.class)
public interface DepartmentMapper extends BaseMapper<Department, DepartmentDTO> {
    @Override
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    DepartmentDTO toDto(Department department);
}
