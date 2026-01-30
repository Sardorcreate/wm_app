package sardorcreate.dto.employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDto {

    private long id;
    private String fullName;
    private String department;
}
