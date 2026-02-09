package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
}
