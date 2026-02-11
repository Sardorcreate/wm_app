package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Computer {

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
    private ComputerType type;

    @Enumerated(EnumType.STRING)
    private ProcessorType processorType;

    @Enumerated(EnumType.STRING)
    private ProcessorVariant processorVariant;

    @Enumerated(EnumType.STRING)
    private RAMType ramType;

    @Enumerated(EnumType.STRING)
    private ROMType romType;

    @Enumerated(EnumType.STRING)
    private ROMVariant romVariant;

    @OneToOne
    @JoinColumn(name = "monitor_id")
    private Monitor monitor;
}
