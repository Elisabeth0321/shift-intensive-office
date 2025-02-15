package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    EmployeeDTO add(EmployeeDTO employeeDTO);

    Page<EmployeeDTO> getByDepartment(int departmentId, Pageable pageable);

    EmployeeDTO promoteToManager(int id);

    EmployeeDTO update(int id, EmployeeDTO employeeDTO);

    void delete(int id);

}
