package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByFullName(String fullName);

    List<User> findUserByDepartment_Id(long departmentId);

    Optional<User> findByLogin(String login);
}
