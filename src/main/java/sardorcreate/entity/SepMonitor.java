package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import sardorcreate.enums.ScreenType;

@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = false")
public class SepMonitor extends Tools{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int size;

    private int refreshRate;

    @Enumerated(EnumType.STRING)
    private ScreenType type;
}
