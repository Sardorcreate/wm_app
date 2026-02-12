package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Tablet;

import java.util.Optional;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, Long>, JpaSpecificationExecutor<Tablet> {

    Optional<Tablet> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
