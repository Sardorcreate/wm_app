package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Computer;

import java.util.Optional;

@Repository
public interface ComputerRepository extends CrudRepository<Computer, Long> {

    Optional<Computer> findByInventoryId_InventoryId(long id);
}
