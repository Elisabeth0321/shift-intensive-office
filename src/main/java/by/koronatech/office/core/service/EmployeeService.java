package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    GetEmployeeDTO add(CreateEmployeeDTO createEmployeeDTO);

    Page<GetEmployeeDTO> getByDepartment(Long departmentId, Pageable pageable);

    GetEmployeeDTO promoteToManager(Long id);

    GetEmployeeDTO update(long id, CreateEmployeeDTO createEmployeeDTO);

    void delete(long id);
}
