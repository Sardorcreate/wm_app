package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ScreenType;

@Getter
@Setter
@Entity
public class SepMonitor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Employee owner;

    private String inventoryId;

    private String model;

    private int size;

    private int refreshRate;

    @Enumerated(EnumType.STRING)
    private ScreenType type;
}
