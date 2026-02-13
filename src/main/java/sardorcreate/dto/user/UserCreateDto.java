package sardorcreate.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCreateDto {

    private String fullName;
    private String login;
    private String password;
    private long department;
}
