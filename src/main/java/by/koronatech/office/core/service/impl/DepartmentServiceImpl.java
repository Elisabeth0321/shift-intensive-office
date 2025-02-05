package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.department.GetDepartmentDTO;
import by.koronatech.office.core.mapper.department.GetDepartmentMapper;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final GetDepartmentMapper getDepartmentMapper;

    private final List<Department> cacheRepository = new ArrayList<>(
            List.of(
                    Department.builder().id(1L).name("Бухгалтерия").build(),
                    Department.builder().id(2L).name("Отдел кадров").build(),
                    Department.builder().id(3L).name("IT-отдел").build(),
                    Department.builder().id(4L).name("Маркетинг").build()
            )
    );

    @Override
    public Page<GetDepartmentDTO> getAllDepartments(Pageable pageable) {
        List<GetDepartmentDTO> departmentDTOs = getDepartmentMapper.toDtos(cacheRepository);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), departmentDTOs.size());

        List<GetDepartmentDTO> pageContent = departmentDTOs.subList(start, end);

        return new PageImpl<>(pageContent, pageable, departmentDTOs.size());
    }

    public Long getDepartmentIdByName(String departmentName) {
        return cacheRepository.stream()
                .filter(department -> department.getName().equalsIgnoreCase(departmentName))
                .map(Department::getId)
                .findFirst()
                .orElse(null);
    }

    public Department findById(long id) {
        return cacheRepository.stream()
                .filter(department -> department.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
