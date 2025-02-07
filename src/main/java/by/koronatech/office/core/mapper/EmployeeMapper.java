package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {

    @Mapping(source = "department.name", target = "departmentName")
    EmployeeDTO toDto(Employee employee);
}



