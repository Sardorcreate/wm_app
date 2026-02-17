package sardorcreate.dto.ip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ToolsStatus;


import java.time.LocalDate;

@Getter
@Setter
@ToString
public class IpDto {

    private long id;
    private long owner;
    private long inventoryId;
    private String model;
    private LocalDate date;
    private String whereFrom;
    private long price;
    private ToolsStatus status;
}
