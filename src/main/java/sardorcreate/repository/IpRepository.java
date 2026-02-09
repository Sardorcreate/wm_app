package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.IpPhone;

import java.util.Optional;

@Repository
public interface IpRepository extends CrudRepository<IpPhone, Long> {

    Optional<IpPhone> findByInventoryId_InventoryId(long id);
}
