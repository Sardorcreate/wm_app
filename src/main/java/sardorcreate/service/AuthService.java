package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.user.AuthResponseDto;
import sardorcreate.dto.user.LoginDto;
import sardorcreate.dto.user.UserDto;
import sardorcreate.entity.User;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.UserMapper;
import sardorcreate.repository.UserRepository;
import sardorcreate.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> login(LoginDto login) {

        User user = userRepository
                .findByLogin(login.getLogin())
                .orElseThrow(() ->
                            new NotExistsException("The user with this login does not exist")
                        );

        UserDto userDto = userMapper.entityToDto(user);

        String jwt = jwtUtil.encode(user.getId(), user.getLogin(), user.getRole().name());

        AuthResponseDto responseDto = new AuthResponseDto();

        responseDto.setJwt(jwt);
        responseDto.setUserDto(userDto);

        return ResponseEntity.ok(responseDto);
    }
}
