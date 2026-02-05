package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByFullName(String fullName);

    List<Employee> findEmployeeByDepartment_Id(long departmentId);
}
