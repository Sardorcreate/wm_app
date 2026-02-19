package sardorcreate.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sardorcreate.config.CustomUserDetail;
import sardorcreate.dto.JWTDto;
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
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override @NonNull
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository
                .findByLogin(login)
                .orElseThrow(() ->
                        new NotExistsException("The user with this login does not exist")
                );

        return new CustomUserDetail(user);
    }

    public ResponseEntity<?> login(LoginDto login) {

        User user = userRepository
                .findByLogin(login.getLogin())
                .orElseThrow(() ->
                            new NotExistsException("The user with this login does not exist")
                        );

        JWTDto jwtDto = new JWTDto();
        jwtDto.setId(user.getId());
        jwtDto.setLogin(user.getLogin());
        jwtDto.setRoles(user.getRoles());

        UserDto userDto = userMapper.entityToDto(user);

        String token = jwtUtil.generateToken(jwtDto);

        AuthResponseDto responseDto = new AuthResponseDto();

        responseDto.setJwt(token);
        responseDto.setUserDto(userDto);

        return ResponseEntity.ok(responseDto);
    }
}
