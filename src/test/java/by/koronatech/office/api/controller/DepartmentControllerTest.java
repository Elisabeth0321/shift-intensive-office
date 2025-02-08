package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.core.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private DepartmentDTO departmentDTO;

    @BeforeEach
    void setUp() {
        departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1);
        departmentDTO.setName("IT-отдел");
    }

    @Test
    void getAllDepartments_ReturnsDepartmentList() throws Exception {
        Page<DepartmentDTO> departmentPage = new PageImpl<>(List.of(departmentDTO), PageRequest.of(0, 10), 1);
        when(departmentService.getAllDepartments(any(PageRequest.class))).thenReturn(departmentPage);

        mockMvc.perform(get("/departments")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("IT-отдел"));
    }
}