package sardorcreate.dto.tablet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.OSType;

@Getter
@Setter
@ToString
public class TabletDto {

    private long id;
    private long owner;
    private long inventoryId;
    private String model;
    private OSType osType;
    private int ram;
    private int rom;
}
