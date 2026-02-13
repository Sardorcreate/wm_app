package sardorcreate.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.Role;
import sardorcreate.enums.UserStatus;

@Getter
@Setter
@ToString
public class UserDto {

    private long id;
    private String fullName;
    private String login;
    private String password;
    private String department;
    private Role role;
    private UserStatus status;
}
