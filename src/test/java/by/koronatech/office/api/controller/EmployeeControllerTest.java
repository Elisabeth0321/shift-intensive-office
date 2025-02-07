package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Лизавета Алексеевна");
        employeeDTO.setDepartmentName("IT-отдел");
    }

    @Test
    void addEmployee_ReturnsCreatedEmployee() throws Exception {
        when(employeeService.add(any(EmployeeDTO.class))).thenReturn(employeeDTO);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Лизавета Алексеевна"));
    }

    @Test
    void getEmployeesByDepartment_ReturnsEmployeeList() throws Exception {
        Page<EmployeeDTO> employeePage = new PageImpl<>(List.of(employeeDTO), PageRequest.of(0, 10), 1);
        when(employeeService.getByDepartment(eq(1L), any(PageRequest.class))).thenReturn(employeePage);

        mockMvc.perform(get("/employees/department/1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Лизавета Алексеевна"));
    }

    @Test
    void promoteToManager_ReturnsUpdatedEmployee() throws Exception {
        when(employeeService.promoteToManager(1L)).thenReturn(employeeDTO);

        mockMvc.perform(patch("/employees/1/promote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Лизавета Алексеевна"));
    }

    @Test
    void updateEmployee_ReturnsUpdatedEmployee() throws Exception {
        when(employeeService.update(eq(1L), any(EmployeeDTO.class))).thenReturn(employeeDTO);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Лизавета Алексеевна"));
    }

    @Test
    void deleteEmployee_ReturnsNoContent() throws Exception {
        Mockito.doNothing().when(employeeService).delete(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }

}
