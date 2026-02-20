package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sardorcreate.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
