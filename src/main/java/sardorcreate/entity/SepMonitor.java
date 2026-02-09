package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ScreenType;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Getter
@Setter
@Entity
public class SepMonitor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Employee owner;

    @OneToOne(optional = false)
    private Inventory inventoryId;

    private String model;
    private Instant date;
    private String whereFrom;
    private long price;

    @Enumerated(EnumType.STRING)
    private ToolsStatus status;

    private int size;

    private int refreshRate;

    @Enumerated(EnumType.STRING)
    private ScreenType type;
}
