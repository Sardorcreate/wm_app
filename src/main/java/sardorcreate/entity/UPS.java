package sardorcreate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sardorcreate.enums.BatteryType;
import sardorcreate.enums.UPSType;

@Getter
@Setter
@Entity
public class UPS extends Tool{

    @Enumerated(EnumType.STRING)
    private BatteryType batteryType;

    @Enumerated(EnumType.STRING)
    private UPSType type;
}
