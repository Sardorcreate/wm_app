package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Commutator {

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
    private boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private ToolsStatus status;

    @Enumerated(EnumType.STRING)
    private CommutatorType type;

    @Enumerated(EnumType.STRING)
    private PortType portType;

    @Enumerated(EnumType.STRING)
    private PortSpeed portSpeed;

    @Enumerated(EnumType.STRING)
    private PortCount portCount;
}
