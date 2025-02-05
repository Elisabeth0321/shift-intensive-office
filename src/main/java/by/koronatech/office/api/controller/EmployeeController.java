package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public GetEmployeeDTO addEmployee(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.add(createEmployeeDTO);
    }

    @GetMapping("/department/{departmentId}")
    public Page<GetEmployeeDTO> getEmployeesByDepartment(@PathVariable Long departmentId, Pageable pageable) {
        return employeeService.getByDepartment(departmentId, pageable);
    }

    @PutMapping("/{id}/promote")
    public GetEmployeeDTO promoteToManager(@PathVariable Long id) {
        return employeeService.promoteToManager(id);
    }

    @PutMapping("/{id}")
    public GetEmployeeDTO update(@PathVariable long id, @RequestBody CreateEmployeeDTO updateEmployeeDTO) {
        return employeeService.update(id, updateEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        employeeService.delete(id);
        log.info("All's good");
    }
}
