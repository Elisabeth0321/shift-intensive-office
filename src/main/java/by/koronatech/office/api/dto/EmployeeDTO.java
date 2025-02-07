package by.koronatech.office.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String departmentName;
    private Double salary;
    private Boolean isManager;
}