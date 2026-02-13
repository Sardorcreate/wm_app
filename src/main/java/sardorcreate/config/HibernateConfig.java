package sardorcreate.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import sardorcreate.util.UserSession;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class HibernateConfig implements AuditorAware<Long> {

    private final UserSession userSession;

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {

        return Optional.ofNullable(userSession.getUser() != null ? userSession.getUser().getId() : null);
    }
}
