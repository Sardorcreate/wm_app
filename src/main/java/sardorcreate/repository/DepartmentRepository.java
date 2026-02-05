package sardorcreate.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Department;
import sardorcreate.enums.DepLevel;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Optional<Department> findByName(String name);

    List<Department> findDepartmentByLevel(DepLevel level);

    List<Department> findDepartmentByParent_Id(long parentId);
}
