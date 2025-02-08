package by.koronatech.office.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {

    @Test
    void shouldCreateEmployeeUsingSetters() {
        Department department = new Department();
        department.setId(1);
        department.setName("Бухгалтерия");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Владимир Владимирович");
        employee.setSalary(5000.0);
        employee.setIsManager(false);
        employee.setDepartment(department);

        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getName()).isEqualTo("Владимир Владимирович");
        assertThat(employee.getSalary()).isEqualTo(5000.0);
        assertThat(employee.getIsManager()).isFalse();
        assertThat(employee.getDepartment()).isEqualTo(department);
    }

    @Test
    void shouldCreateEmployeeUsingBuilder() {
        Department department = new Department(1, "Бухгалтерия");

        Employee employee = Employee.builder()
                .id(2)
                .name("Альбус Дамблдор")
                .salary(6000000.0)
                .isManager(true)
                .department(department)
                .build();

        assertThat(employee.getId()).isEqualTo(2);
        assertThat(employee.getName()).isEqualTo("Альбус Дамблдор");
        assertThat(employee.getSalary()).isEqualTo(6000000.0);
        assertThat(employee.getIsManager()).isTrue();
        assertThat(employee.getDepartment()).isEqualTo(department);
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        Department department = new Department(1, "Бухгалтерия");

        Employee e1 = Employee.builder().id(1).name("Виктор Крам").salary(3000.0).isManager(false).department(department).build();
        Employee e2 = Employee.builder().id(1).name("Виктор Крам").salary(3000.0).isManager(false).department(department).build();

        assertThat(e1).isEqualTo(e2);
        assertThat(e1.hashCode()).isEqualTo(e2.hashCode());
    }
}