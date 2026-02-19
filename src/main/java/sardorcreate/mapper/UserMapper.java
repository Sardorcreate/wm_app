package sardorcreate.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sardorcreate.dto.user.UserCreateDto;
import sardorcreate.dto.user.UserDto;
import sardorcreate.entity.Department;
import sardorcreate.entity.User;
import sardorcreate.enums.UserStatus;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User dtoToEntity(UserCreateDto dto, Department dep) {

        User user = new User();

        user.setFullName(dto.getFullName());
        user.setDepartment(dep);
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles("ROLE_ADMIN");

        return user;
    }

    public UserDto entityToDto(User save) {

        UserDto dto = new UserDto();

        dto.setId(save.getId());
        dto.setFullName(save.getFullName());
        dto.setDepartment(save.getDepartment().getName());
        dto.setLogin(save.getLogin());
        dto.setStatus(save.getStatus());
        dto.setRoles(save.getRoles());

        return dto;
    }
}
