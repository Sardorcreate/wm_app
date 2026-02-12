package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Scanner;

import java.util.Optional;

@Repository
public interface ScannerRepository extends JpaRepository<Scanner, Long>, JpaSpecificationExecutor<Scanner> {

    Optional<Scanner> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
