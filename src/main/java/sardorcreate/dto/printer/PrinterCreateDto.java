package sardorcreate.dto.printer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.PrinterType;

@Getter
@Setter
@ToString
public class PrinterCreateDto {

    private long owner;
    private long inventoryId;
    private String model;
    private PrinterType type;
    private boolean isColored;
    private int colorCount;
}
