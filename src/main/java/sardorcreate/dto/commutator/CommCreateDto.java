package sardorcreate.dto.commutator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.CommutatorType;
import sardorcreate.enums.PortCount;
import sardorcreate.enums.PortSpeed;
import sardorcreate.enums.PortType;

@Getter
@Setter
@ToString
public class CommCreateDto {

    private long inventoryId;
    private String model;
    private String whereFrom;
    private long price;
    private CommutatorType type;
    private PortType portType;
    private PortSpeed portSpeed;
    private PortCount portCount;
}
