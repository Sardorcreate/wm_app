package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import sardorcreate.enums.PrinterType;

@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = false")
public class Printer extends Tools{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    private PrinterType type;

    private boolean isColored;

    private int colorCount;

}
