package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import sardorcreate.enums.BatteryType;
import sardorcreate.enums.UPSType;

@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = false")
public class UPS extends Tools{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    private BatteryType batteryType;

    @Enumerated(EnumType.STRING)
    private UPSType type;
}
