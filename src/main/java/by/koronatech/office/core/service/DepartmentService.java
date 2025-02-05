package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.department.GetDepartmentDTO;
import by.koronatech.office.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Page<GetDepartmentDTO> getAllDepartments(Pageable pageable);

    Long getDepartmentIdByName(String departmentName);

    Department findById(long id);
}
