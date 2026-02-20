package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import sardorcreate.entity.Notification;
import sardorcreate.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;

    public void sendAdminNotification(String message) {

        Notification notification = new Notification();
        notification.setMessage(message);

        notificationRepository.save(notification);

        messagingTemplate.convertAndSend("/topic/admin-notifications", message);
    }

    public ResponseEntity<?> getUnreadNotifications() {

        List<Notification> notifications = notificationRepository.findByReadFalse();

        return ResponseEntity.ok(notifications);
    }

    public void setRead(long id) {

        List<Notification> list = notificationRepository.findByReadFalse();

        list.stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .ifPresent(n -> {
                    n.setRead(true);
                    list.remove(n);
                });
    }
}
