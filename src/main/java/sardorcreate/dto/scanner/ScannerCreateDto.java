package sardorcreate.dto.scanner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ScannerType;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ScannerCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private BigDecimal price;
    private ScannerType type;
    private int dpi;
}
