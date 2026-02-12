package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.UPS;

import java.util.Optional;

@Repository
public interface UPSRepository extends JpaRepository<UPS, Long>, JpaSpecificationExecutor<UPS> {

    Optional<UPS> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
