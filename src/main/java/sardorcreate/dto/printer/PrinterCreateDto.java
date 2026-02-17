package sardorcreate.dto.printer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.PrinterType;



@Getter
@Setter
@ToString
public class PrinterCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private long price;
    private PrinterType type;
    private boolean isColored;
    private int colorCount;
}
