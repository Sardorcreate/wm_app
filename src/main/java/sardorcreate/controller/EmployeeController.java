package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.employee.EmployeeCreateDto;
import sardorcreate.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeCreateDto dto) {

        return employeeService.createEmployee(dto);
    }

    @GetMapping("/get/dep_id/{id}")
    public ResponseEntity<?> getEmployeeByDep(@PathVariable long id) {

        return employeeService.getEmployeeByDep(id);
    }
}