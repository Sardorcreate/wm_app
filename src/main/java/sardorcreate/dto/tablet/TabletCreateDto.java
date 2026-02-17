package sardorcreate.dto.tablet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.OSType;



@Getter
@Setter
@ToString
public class TabletCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private long price;
    private OSType osType;
    private int ram;
    private int rom;
}
