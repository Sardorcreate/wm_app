package sardorcreate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.CommutatorType;
import sardorcreate.enums.PortType;

@Getter
@Setter
@ToString
public class GetCommPortCount {

    private CommutatorType cType;
    private PortType pType;
}
