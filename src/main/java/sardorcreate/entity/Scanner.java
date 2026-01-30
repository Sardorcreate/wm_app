package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.ScannerType;

@Getter
@Setter
@Entity
public class Scanner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Employee owner;

    private String inventoryId;

    private String model;

    @Enumerated(EnumType.STRING)
    private ScannerType type;

    private int dpi;
}
