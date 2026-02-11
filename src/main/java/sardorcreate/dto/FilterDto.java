package sardorcreate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.ToolsStatus;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class FilterDto {

    private LocalDate date;
    private Long inventoryId;
    private ToolsStatus status;
}
