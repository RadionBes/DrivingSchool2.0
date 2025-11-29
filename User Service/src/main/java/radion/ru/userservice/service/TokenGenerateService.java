package radion.ru.userservice.service;

import org.springframework.security.core.GrantedAuthority;
import radion.ru.userservice.dto.AuthentificationResponse;
import radion.ru.userservice.entity.UserSchool;

import java.util.Collection;

public interface TokenGenerateService {

    String generateAccessToken(String email);

    String generateRefreshToken(UserSchool userSchool);
    AuthentificationResponse reloadToken(String refreshToken);
}
