package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Computer;

import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>, JpaSpecificationExecutor<Computer> {

    Optional<Computer> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
