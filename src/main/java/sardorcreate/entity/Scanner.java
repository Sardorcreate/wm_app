package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import sardorcreate.enums.ScannerType;
import sardorcreate.enums.ToolsStatus;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = false")
public class Scanner extends Tools{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    private ScannerType type;

    private int dpi;
}
