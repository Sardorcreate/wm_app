package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ToolsStatus;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class IpPhone {

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
}