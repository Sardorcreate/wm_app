package sardorcreate.dto.ups;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.BatteryType;
import sardorcreate.enums.OSType;
import sardorcreate.enums.UPSType;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class UPSCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private BigDecimal price;
    private UPSType type;
    private BatteryType batteryType;
}
