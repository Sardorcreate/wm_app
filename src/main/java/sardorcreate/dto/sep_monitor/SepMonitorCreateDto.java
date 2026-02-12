package sardorcreate.dto.sep_monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ScreenType;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class SepMonitorCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private BigDecimal price;
    private int size;
    private int refreshRate;
    private ScreenType monType;
}
