package sardorcreate.dto.computer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.dto.monitor.MonitorCreateDto;
import sardorcreate.enums.*;

@Getter
@Setter
@ToString
public class ComputerCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private long price;
    private ComputerType type;
    private ProcessorType procType;
    private ProcessorVariant procVariant;
    private RAMType ramType;
    private ROMType romType;
    private ROMVariant romVariant;
    private MonitorCreateDto monDto;
}
