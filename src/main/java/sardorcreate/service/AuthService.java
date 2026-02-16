package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import sardorcreate.config.JWTProvider;
import sardorcreate.dto.user.AuthResponseDto;
import sardorcreate.dto.user.LoginDto;
import sardorcreate.dto.user.UserDto;
import sardorcreate.entity.User;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.UserMapper;
import sardorcreate.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTProvider jWTProvider;
    private final UserMapper userMapper;

    public ResponseEntity<?> login(LoginDto login) {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        login.getLogin(),
                        login.getPassword()
                ));

        User user = userRepository
                .findByLogin(login.getLogin())
                .orElseThrow(() ->
                            new NotExistsException("The user with this login does not exist")
                        );

        UserDto userDto = userMapper.entityToDto(user);
        String jwt = jWTProvider.generateAccessToken(user);

        AuthResponseDto responseDto = new AuthResponseDto();

        responseDto.setJwt(jwt);
        responseDto.setUserDto(userDto);

        return ResponseEntity.ok(responseDto);
    }
}
