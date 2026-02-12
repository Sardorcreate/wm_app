package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Printer;

import java.util.Optional;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long>, JpaSpecificationExecutor<Printer> {

    Optional<Printer> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
