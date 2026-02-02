package sardorcreate.dto.sep_monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ScreenType;

@Getter
@Setter
@ToString
public class SepMonitorCreateDto {

    private long owner;
    private long inventoryId;
    private String model;
    private int size;
    private int refreshRate;
    private ScreenType type;
}
