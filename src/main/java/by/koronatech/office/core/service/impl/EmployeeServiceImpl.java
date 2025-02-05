package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;
import by.koronatech.office.core.mapper.employee.CreateEmployeeMapper;
import by.koronatech.office.core.mapper.employee.GetEmployeeMapper;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import by.koronatech.office.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final GetEmployeeMapper getEmployeeMapper;
    private final CreateEmployeeMapper createEmployeeMapper;

    private final DepartmentService departmentService;

    private final List<Employee> cacheRepository = new ArrayList<>(
            List.of(
                    Employee.builder().id(1L).name("Сидоров Василий").salary(520.3).department("Бухгалтерия").manager(false).build(),
                    Employee.builder().id(2L).name("Полякова Светлана").salary(500.1).department("Бухгалтерия").manager(false).build()
            )
    );

    @Override
    public GetEmployeeDTO add(CreateEmployeeDTO createEmployeeDTO) {
        return getEmployeeMapper.toDto(createEmployeeMapper.toEntity(createEmployeeDTO));
    }

    @Override
    public Page<GetEmployeeDTO> getByDepartment(Long departmentId, Pageable pageable) {
        String departmentName = departmentService.findById(departmentId).getName();

        if (departmentName == null) {
            return Page.empty();
        }

        List<GetEmployeeDTO> filteredEmployees = cacheRepository.stream()
                .filter(emp -> emp.getDepartment().equals(departmentName))
                .map(getEmployeeMapper::toDto)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filteredEmployees.size());

        if (start >= filteredEmployees.size()) {
            return Page.empty(pageable);
        }

        return new PageImpl<>(filteredEmployees.subList(start, end), pageable, filteredEmployees.size());

    }

    @Override
    public GetEmployeeDTO promoteToManager(Long id) {
        Employee employee = findById(id);
        employee.setManager(true);
        return null;
    }

    @Override
    public GetEmployeeDTO update(long id, CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = findById(id);
        createEmployeeMapper.merge(employee, createEmployeeDTO);
        return null;
    }

    @Override
    public void delete(long id) {
        cacheRepository.removeIf(employee -> employee.getId().equals(id));
    }

    private Employee findById(long id) {
        return cacheRepository.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

}
