package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.CommutatorType;
import sardorcreate.enums.PortCount;
import sardorcreate.enums.PortSpeed;
import sardorcreate.enums.PortType;

@Getter
@Setter
@Entity
public class Commutator {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Employee owner;

    private String inventoryId;

    private String model;

    @Enumerated(EnumType.STRING)
    private CommutatorType type;

    @Enumerated(EnumType.STRING)
    private PortType portType;

    @Enumerated(EnumType.STRING)
    private PortSpeed portSpeed;

    @Enumerated(EnumType.STRING)
    private PortCount portCount;
}
