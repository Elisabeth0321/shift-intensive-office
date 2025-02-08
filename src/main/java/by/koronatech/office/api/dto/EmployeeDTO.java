package by.koronatech.office.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String departmentName;
    private Double salary;
    private Boolean isManager;
}