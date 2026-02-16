package sardorcreate.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthResponseDto {

    private String jwt;
    private UserDto userDto;
}
