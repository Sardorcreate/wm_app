package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.SepMonitor;

import java.util.Optional;

@Repository
public interface SepMonRepository extends JpaRepository<SepMonitor, Long>, JpaSpecificationExecutor<SepMonitor> {

    Optional<SepMonitor> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
