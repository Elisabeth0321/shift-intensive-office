package by.koronatech.office.api.dto.employee;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDTO {
    private Long id;
    private String name;
    private Double salary;
    private String department;
    private Boolean manager;
}
