package sardorcreate.dto.commutator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CommDto {

    private long id;
    private String owner;
    private long inventoryId;
    private String model;
    private LocalDate date;
    private String whereFrom;
    private BigDecimal price;
    private ToolsStatus status;
    private CommutatorType type;
    private PortType portType;
    private PortSpeed portSpeed;
    private PortCount portCount;
}
