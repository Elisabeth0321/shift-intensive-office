package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {

    @Override
    EmployeeDTO toDto(Employee employee);

    @Override
    Employee toEntity(EmployeeDTO dto);
}



