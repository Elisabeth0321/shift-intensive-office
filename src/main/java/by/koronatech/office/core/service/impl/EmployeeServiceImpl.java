package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import by.koronatech.office.core.service.exception.NotFoundException;
import by.koronatech.office.entity.Employee;
import by.koronatech.office.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO add(EmployeeDTO createEmployeeDTO) {
        Employee employee = employeeMapper.toEntity(createEmployeeDTO);

        employee.setDepartment(departmentService.findByName(createEmployeeDTO.getDepartmentName()));

        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public Page<EmployeeDTO> getByDepartment(int departmentId, Pageable pageable) {
        return employeeRepository.findByDepartmentId(departmentId, pageable)
                .map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDTO promoteToManager(int id) {
        Employee employee = findById(id);

        if (employee.getIsManager() != null && employee.getIsManager()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Сотрудник с ID " + id + " уже является менеджером");
        }

        employee.setIsManager(true);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDTO update(int id, EmployeeDTO createEmployeeDTO) {
        Employee employee = findById(id);
        employeeMapper.merge(employee, createEmployeeDTO);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    private Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Сотрудник с ID " + id + " не найден"));
    }

}
