package sardorcreate.dto.ups;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.BatteryType;
import sardorcreate.enums.ToolsStatus;
import sardorcreate.enums.UPSType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UPSDto {

    private long id;
    private long owner;
    private long inventoryId;
    private String model;
    private LocalDate date;
    private String whereFrom;
    private BigDecimal price;
    private ToolsStatus status;
    private UPSType type;
    private BatteryType batteryType;
}
