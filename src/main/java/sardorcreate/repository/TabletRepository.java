package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Tablet;

import java.util.Optional;

@Repository
public interface TabletRepository extends CrudRepository<Tablet, Long> {

    Optional<Tablet> findByInventoryId(long inventoryId);
}
