package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.employee.EmployeeCreateDto;
import sardorcreate.dto.employee.EmployeeDto;
import sardorcreate.entity.Department;
import sardorcreate.entity.Employee;
import sardorcreate.repository.EmployeeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public ResponseEntity<?> createEmployee(EmployeeCreateDto dto) {

        Optional<Employee> byFullName = employeeRepository.findByFullName(dto.getFullName());

        Department dep = departmentService.getDepartment(dto.getDepartment());

        if (byFullName.isPresent()) {
            ResponseEntity.badRequest().body("The employee with this name already exists!!!");
        }

        Employee employee = new Employee();

        employee.setFullName(dto.getFullName());
        employee.setDepartment(dep);

        Employee save = employeeRepository.save(employee);

        EmployeeDto newDto = new EmployeeDto();

        newDto.setId(save.getId());
        newDto.setFullName(save.getFullName());
        newDto.setDepartment(save.getDepartment().getName());

        return ResponseEntity.ok(newDto);
    }

    public Employee getEmployee (long id) {

        Optional<Employee> byId = employeeRepository.findById(id);

        if (byId.isEmpty()) {
            throw new RuntimeException("This employee does not exist!!!");
        }

        return byId.get();
    }
}
