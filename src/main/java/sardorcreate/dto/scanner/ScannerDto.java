package sardorcreate.dto.scanner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ScannerType;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Getter
@Setter
@ToString
public class ScannerDto {

    private long id;
    private long owner;
    private long inventoryId;
    private String model;
    private Instant date;
    private String whereFrom;
    private long price;
    private ToolsStatus status;
    private ScannerType type;
    private int dpi;
}
