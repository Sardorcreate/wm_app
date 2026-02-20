package sardorcreate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sardorcreate.entity.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByReadFalse();
}
