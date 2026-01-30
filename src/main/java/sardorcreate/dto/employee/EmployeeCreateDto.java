package sardorcreate.dto.employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeCreateDto {

    private String fullName;
    private long department;
}
