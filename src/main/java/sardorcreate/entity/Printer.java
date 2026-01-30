package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.PrinterType;

@Getter
@Setter
@Entity
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Employee owner;

    private String inventoryId;

    private String model;

    @Enumerated(EnumType.STRING)
    private PrinterType type;

    private boolean isColored;

    private int colorCount;

}
