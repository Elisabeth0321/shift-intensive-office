package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapper.class)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {

    @Override
    @Mapping(source = "department.name", target = "departmentName")
    EmployeeDTO toDto(Employee employee);

    @Override
    @Mapping(target = "department", ignore = true)
    Employee toEntity(EmployeeDTO dto);
}



