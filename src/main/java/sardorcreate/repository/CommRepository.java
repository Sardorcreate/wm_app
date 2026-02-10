package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Commutator;

import java.util.Optional;

@Repository
public interface CommRepository extends CrudRepository<Commutator, Long> {

    Optional<Commutator> findByInventoryId_InventoryId(long id);
}
