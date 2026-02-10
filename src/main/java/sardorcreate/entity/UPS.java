package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.BatteryType;
import sardorcreate.enums.ToolsStatus;
import sardorcreate.enums.UPSType;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class UPS {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Employee owner;

    @OneToOne(optional = false)
    private Inventory inventoryId;

    private String model;
    private LocalDate date;
    private String whereFrom;
    private long price;

    @Enumerated(EnumType.STRING)
    private ToolsStatus status;

    @Enumerated(EnumType.STRING)
    private BatteryType batteryType;

    @Enumerated(EnumType.STRING)
    private UPSType type;
}
