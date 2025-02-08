package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO createEmployeeDTO) {
        return employeeService.add(createEmployeeDTO);
    }

    @GetMapping("/department/{departmentId}")
    public Page<EmployeeDTO> getEmployeesByDepartment(@PathVariable int departmentId, Pageable pageable) {
        return employeeService.getByDepartment(departmentId, pageable);
    }

    @PatchMapping("/{id}/promote")
    public EmployeeDTO promoteToManager(@PathVariable int id) {
        return employeeService.promoteToManager(id);
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable int id, @RequestBody EmployeeDTO updateEmployeeDTO) {
        return employeeService.update(id, updateEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.delete(id);
        log.info("All's good");
    }
}
