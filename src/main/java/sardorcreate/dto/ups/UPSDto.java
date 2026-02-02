package sardorcreate.dto.ups;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.BatteryType;
import sardorcreate.enums.OSType;
import sardorcreate.enums.UPSType;

@Getter
@Setter
@ToString
public class UPSDto {

    private long id;
    private long owner;
    private long inventoryId;
    private String model;
    private UPSType type;
    private BatteryType batteryType;
}
