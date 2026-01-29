package sardorcreate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ComputerType;
import sardorcreate.enums.ProcessorType;

@Getter
@Setter
@ToString
public class GetCompProcType {
    private ComputerType compType;
    private ProcessorType procType;
}
