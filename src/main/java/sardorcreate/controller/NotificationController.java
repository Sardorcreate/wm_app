package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.service.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/get/unread")
    public ResponseEntity<?> getUnreadNotifications() {

        return notificationService.getUnreadNotifications();
    }

    @PostMapping("/set_read/{id}")
    public void setRead(@PathVariable long id) {

        notificationService.setRead(id);
    }
}
