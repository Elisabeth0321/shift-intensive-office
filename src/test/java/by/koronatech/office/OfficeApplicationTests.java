package by.koronatech.office;

import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OfficeApplication.class)
class OfficeApplicationTests {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@Test
	void contextLoads() {
		assertThat(departmentService).isNotNull();
		assertThat(employeeService).isNotNull();
	}
}
