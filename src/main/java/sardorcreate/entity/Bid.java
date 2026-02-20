package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.BidStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Bid {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private User sender;

    private String title;
    private String body;
    private int count;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private BidStatus status;
}
