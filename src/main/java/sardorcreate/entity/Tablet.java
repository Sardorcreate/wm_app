package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import sardorcreate.enums.OSType;

@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = false")
public class Tablet extends Tools{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    private OSType osType;

    private int ram;
    private int rom;
}
