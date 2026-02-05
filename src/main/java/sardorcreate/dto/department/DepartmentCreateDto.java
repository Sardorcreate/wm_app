package sardorcreate.dto.department;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.DepLevel;

@Getter
@Setter
@ToString
public class DepartmentCreateDto {

    private String name;
    private DepLevel level;
    private long parentId;
}
