package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.department.DepartmentCreateDto;
import sardorcreate.service.DepartmentService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentCreateDto dto) {

        return departmentService.createDepartment(dto);
    }

    @GetMapping("/get/level=1")
    public ResponseEntity<?> getLevelFirstDep() {

        return departmentService.getFirstLevelDep();
    }

    @GetMapping("get/{parentId}")
    public ResponseEntity<?> getDepByParent(@PathVariable long parentId) {

        return departmentService.getDepByParent(parentId);
    }
}
