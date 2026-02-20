package sardorcreate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String message;
    private boolean read = false;
    private LocalDateTime createdAt = LocalDateTime.now();
}
