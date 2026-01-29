package sardorcreate.dto.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ScreenType;

@Getter
@Setter
@ToString
public class MonitorCreateDto {

    private String model;
    private int size;
    private int refreshRate;
    private ScreenType type;

}
