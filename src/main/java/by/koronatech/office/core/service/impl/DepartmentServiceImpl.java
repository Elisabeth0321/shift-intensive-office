package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.core.mapper.DepartmentMapper;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.exception.NotFoundException;
import by.koronatech.office.entity.Department;
import by.koronatech.office.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<DepartmentDTO> getAllDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(departmentMapper::toDto);
    }

    public Long getDepartmentIdByName(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(Department::getId)
                .orElse(null);
    }

    public Department findById(long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Департамент с ID " + id + " не найден"));
    }

    public Department findByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Департамент " + name + " не найден"));
    }

}
