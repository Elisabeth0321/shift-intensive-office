package by.koronatech.office.core.mapper.employee;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.core.mapper.BaseMapper;
import by.koronatech.office.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface CreateEmployeeMapper extends BaseMapper<Employee, CreateEmployeeDTO>{
}
