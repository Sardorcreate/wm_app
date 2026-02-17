package sardorcreate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JWTDto {

    private long id;
    private String login;
    private String role;
}
