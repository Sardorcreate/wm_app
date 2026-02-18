package sardorcreate.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.UserStatus;

@Getter
@Setter
@ToString
public class UserDto {

    private long id;
    private String fullName;
    private String login;
    private String department;
    private String roles;
    private UserStatus status;
}
