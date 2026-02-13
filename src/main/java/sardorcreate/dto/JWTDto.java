package sardorcreate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sardorcreate.enums.Role;

@Getter
@Setter
@ToString
public class JWTDto {

    private long id;
    private String login;
    private Role role;
}
