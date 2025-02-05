package by.koronatech.office.core.mapper.department;

import by.koronatech.office.api.dto.department.GetDepartmentDTO;
import by.koronatech.office.core.mapper.BaseMapper;
import by.koronatech.office.entity.Department;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface GetDepartmentMapper extends BaseMapper<Department, GetDepartmentDTO> {
}
