package sardorcreate.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sardorcreate.entity.User;
import sardorcreate.exception.NotExistsException;
import sardorcreate.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository
                .findByLogin(username)
                .orElseThrow(
                        () -> new NotExistsException("The user with this login does not exist")
                        );

        return new CustomUserDetail(user);
    }
}
