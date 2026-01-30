package sardorcreate.dto.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ScreenType;

@Getter
@Setter
@ToString
public class MonitorDto {

    private long id;
    private int size;
    private int refreshRate;
    private ScreenType type;
}
