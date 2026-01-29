package sardorcreate.dto.department;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentCreateDto {

    private String name;
    private long parentId;
}
