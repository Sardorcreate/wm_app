package sardorcreate.dto.scanner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ScannerType;

@Getter
@Setter
@ToString
public class ScannerCreateDto {

    private long owner;
    private long inventoryId;
    private String model;
    private ScannerType type;
    private int dpi;
}
