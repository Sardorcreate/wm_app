package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Commutator;

import java.util.Optional;

@Repository
public interface CommRepository extends JpaRepository<Commutator, Long>, JpaSpecificationExecutor<Commutator> {

    Optional<Commutator> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
