package by.koronatech.office.repository;

import by.koronatech.office.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // @EntityGraph(attributePaths = "department")
    Page<Employee> findByDepartmentId(Integer department, Pageable pageable);
}
