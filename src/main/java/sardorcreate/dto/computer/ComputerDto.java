package sardorcreate.dto.computer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.enums.*;

import java.time.Instant;

@Getter
@Setter
@ToString
public class ComputerDto {

    private long id;
    private String owner;
    private long inventoryId;
    private String model;
    private Instant date;
    private String whereFrom;
    private long price;
    private ToolsStatus status;
    private ComputerType type;
    private ProcessorType procType;
    private ProcessorVariant procVariant;
    private RAMType ramType;
    private ROMType romType;
    private ROMVariant romVariant;
    private MonitorDto monitorDto;
}
