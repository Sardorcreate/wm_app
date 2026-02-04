package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.BatteryType;
import sardorcreate.enums.ToolsStatus;
import sardorcreate.enums.UPSType;

import java.time.Instant;

@Getter
@Setter
@Entity
public class UPS {

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

    @Enumerated(EnumType.STRING)
    private BatteryType batteryType;

    @Enumerated(EnumType.STRING)
    private UPSType type;
}
