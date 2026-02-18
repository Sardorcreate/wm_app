package sardorcreate.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sardorcreate.entity.User;
import sardorcreate.enums.UserStatus;

import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private final User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == UserStatus.ACTIVE;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == UserStatus.ACTIVE;
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String role = user.getRoles();

        if (role == null || role.trim().isEmpty()) {

            return List.of();
        }

        role = role.trim();

        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        return List.of(new SimpleGrantedAuthority(role));
    }
}
