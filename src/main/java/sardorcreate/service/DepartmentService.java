package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.department.DepartmentCreateDto;
import sardorcreate.dto.department.DepartmentDto;
import sardorcreate.entity.Department;
import sardorcreate.enums.DepLevel;
import sardorcreate.repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;
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
        dep.setLevel(dto.getLevel());

        if (dto.getParentId() != 0) {

            Department depart = getDepartment(dto.getParentId());

            dep.setParent(depart);
        }

        Department save = departmentRepository.save(dep);

        DepartmentDto depDto = getDto(save);

        return ResponseEntity.ok(depDto);
    }

    public Department getDepartment(long id) {

        Optional<Department> byId = departmentRepository.findById(id);

        if (byId.isEmpty()) {
            ResponseEntity.badRequest().body("There is no Department with such ID!!!");
        }

        return byId.get();
    }

    public ResponseEntity<?> getFirstLevelDep() {

        List<Department> byLevel = departmentRepository.findDepartmentByLevel(DepLevel.FIRST);

        if (byLevel.isEmpty()) {
            throw new RuntimeException("There is no any first level departments!");
        }

        List<DepartmentDto> dtos = new ArrayList<>();

        for (Department department : byLevel) {
            DepartmentDto depDto = getDto(department);

            dtos.add(depDto);
        }

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getDepByParent(long parentId) {

        List<Department> departmentByParentId = departmentRepository.findDepartmentByParent_Id(parentId);

        if (departmentByParentId.isEmpty()) {
            throw new RuntimeException("There is no any department with such parent_id");
        }

        List<DepartmentDto> dtos = new ArrayList<>();

        for (Department department : departmentByParentId) {
            DepartmentDto dto = getDto(department);

            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
    }

    public DepartmentDto getDto (Department dep) {
        DepartmentDto depDto = new DepartmentDto();

        depDto.setId(dep.getId());
        depDto.setName(dep.getName());
        depDto.setLevel(dep.getLevel());

        if (dep.getParent() != null) {
            depDto.setParent(dep.getParent().getName());
        }

        return depDto;
    }
}
