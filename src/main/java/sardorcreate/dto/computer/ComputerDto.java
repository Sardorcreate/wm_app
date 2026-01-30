package sardorcreate.dto.computer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.enums.*;

@Getter
@Setter
@ToString
public class ComputerDto {

    private long id;
    private String owner;
    private String inventoryId;
    private String model;
    private ComputerType type;
    private ProcessorType procType;
    private ProcessorVariant procVariant;
    private RAMType ramType;
    private ROMType romType;
    private ROMVariant romVariant;
    private MonitorDto dto;
}
