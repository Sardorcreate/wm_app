package sardorcreate.dto.printer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.PrinterType;
import sardorcreate.enums.ToolsStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PrinterDto {

    private long id;
    private long owner;
    private long inventoryId;
    private String model;
    private LocalDate date;
    private String whereFrom;
    private BigDecimal price;
    private ToolsStatus status;
    private PrinterType type;
    private boolean isColored;
    private int colorCount;
}
