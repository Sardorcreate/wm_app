package sardorcreate.dto.tablet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.OSType;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TabletCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private BigDecimal price;
    private OSType osType;
    private int ram;
    private int rom;
}
