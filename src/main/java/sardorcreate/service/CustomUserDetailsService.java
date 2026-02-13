package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sardorcreate.exception.NotExistsException;
import sardorcreate.repository.UserRepository;
import sardorcreate.util.MessageService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        if (login.isBlank()) {
            throw new UsernameNotFoundException(MessageService.getMessage("NULL_LOGIN_FROM_TOKEN"));
        }

        return (UserDetails) userRepository.findByLogin(login).orElseThrow(
                () -> new NotExistsException(MessageService.getMessage("LOGIN_NOT_FOUND")));
    }
}
