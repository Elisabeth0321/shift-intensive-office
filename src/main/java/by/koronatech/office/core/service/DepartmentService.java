package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    Page<DepartmentDTO> getAllDepartments(Pageable pageable);

    Integer  getDepartmentIdByName(String departmentName);

    Department findById(int id);

    Department findByName(String departmentName);
}
