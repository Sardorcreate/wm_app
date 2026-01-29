package sardorcreate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sardorcreate.entity.Monitor;

@Repository
public interface MonitorRepository extends CrudRepository<Monitor, Long> {
}
