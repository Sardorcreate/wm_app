package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.department.DepartmentCreateDto;
import sardorcreate.dto.department.DepartmentDto;
import sardorcreate.entity.Department;
import sardorcreate.repository.DepartmentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {


    private final DepartmentRepository departmentRepository;

    public ResponseEntity<?> createDepartment(DepartmentCreateDto dto) {

        Optional<Department> byName = departmentRepository.findByName(dto.getName());

        if (byName.isPresent()) {
            ResponseEntity.badRequest().body("This department has already been created!!!");
        }

        Department dep = new Department();

        dep.setName(dto.getName());

        if (dto.getParentId() != 0) {

            Department depart = getDepartment(dto.getParentId());

            dep.setParent(depart);
        }

        Department save = departmentRepository.save(dep);

        DepartmentDto depDto = new DepartmentDto();

        depDto.setId(save.getId());
        depDto.setName(save.getName());
        depDto.setParent(save.getParent().getName());

        return ResponseEntity.ok(depDto);
    }

    public Department getDepartment(long id) {

        Optional<Department> byId = departmentRepository.findById(id);

        if (byId.isEmpty()) {
            ResponseEntity.badRequest().body("There is no Department with such ID!!!");
        }

        return byId.get();
    }
}
