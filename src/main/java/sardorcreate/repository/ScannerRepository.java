package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Scanner;

import java.util.Optional;

@Repository
public interface ScannerRepository extends CrudRepository<Scanner, Long> {

    Optional<Scanner> findByInventoryId(long inventoryId);
}
