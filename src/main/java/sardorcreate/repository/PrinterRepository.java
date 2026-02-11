package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Printer;

import java.util.Optional;

@Repository
public interface PrinterRepository extends CrudRepository<Printer, Long> {

    Optional<Printer> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
