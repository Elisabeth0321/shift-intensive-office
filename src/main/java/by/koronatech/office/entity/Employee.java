package by.koronatech.office.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private Double salary;
    private String department;
    private Boolean manager;
}
