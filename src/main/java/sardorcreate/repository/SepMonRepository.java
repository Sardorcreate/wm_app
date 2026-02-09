package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.SepMonitor;

import java.util.Optional;

@Repository
public interface SepMonRepository extends CrudRepository<SepMonitor, Long> {

    Optional<SepMonitor> findByInventoryId_InventoryId(long id);
}
