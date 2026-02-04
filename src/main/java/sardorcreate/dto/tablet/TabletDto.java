package sardorcreate.dto.tablet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.OSType;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Getter
@Setter
@ToString
public class TabletDto {

    private long id;
    private long owner;
    private long inventoryId;
    private String model;
    private Instant date;
    private String whereFrom;
    private long price;
    private ToolsStatus status;
    private OSType osType;
    private int ram;
    private int rom;
}
