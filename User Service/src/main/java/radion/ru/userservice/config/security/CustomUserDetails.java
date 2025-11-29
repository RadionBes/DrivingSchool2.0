package radion.ru.userservice.config.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import radion.ru.userservice.entity.UserSchool;

import java.util.Collection;

@Setter
@Getter
@Builder
public class CustomUserDetails implements UserDetails {
    private UserSchool userSchool;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userSchool.getAuthorities();
    }

    @Override
    public @Nullable String getPassword() {
        return userSchool.getPassword();
    }

    @Override
    public String getUsername() {
        return userSchool.getEmail();
    }
}
