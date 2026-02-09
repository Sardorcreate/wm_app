package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.UPS;

import java.util.Optional;

@Repository
public interface UPSRepository extends CrudRepository<UPS, Long> {

    Optional<UPS> findByInventoryId_InventoryId(long id);
}
