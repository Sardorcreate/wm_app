package sardorcreate.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sardorcreate.dto.user.UserDto;
import sardorcreate.entity.User;
import sardorcreate.mapper.UserMapper;

@Component
@RequiredArgsConstructor
public class UserSession {

    private final UserMapper userMapper;

    public UserDto getUser() {

        UserDto dto = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof User) {

            dto = userMapper.entityToDto((User) auth.getPrincipal());
        }

        return dto;
    }
}
