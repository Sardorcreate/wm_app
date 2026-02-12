package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.IpPhone;

import java.util.Optional;

@Repository
public interface IpRepository extends JpaRepository<IpPhone, Long>, JpaSpecificationExecutor<IpPhone> {

    Optional<IpPhone> findByInventoryId_InventoryIdAndIsDeletedFalse(long id);
}
