package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Getter
@Setter
@Entity
public class IpPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Employee owner;

    private long inventoryId;
    private String model;
    private Instant date;
    private String whereFrom;
    private long price;

    @Enumerated(EnumType.STRING)
    private ToolsStatus status;
}