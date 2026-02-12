package sardorcreate.dto.printer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.PrinterType;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PrinterCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private BigDecimal price;
    private PrinterType type;
    private boolean isColored;
    private int colorCount;
}
