package radion.ru.userservice.service;

import radion.ru.userservice.entity.RefreshTokenEntity;
import radion.ru.userservice.entity.UserSchool;

public interface TokenService {
    void saveRefreshToken(RefreshTokenEntity refreshTokenEntity);
    void deleteAllRefreshTokensByUser(UserSchool userSchool);
    boolean ifPresentRefreshToken(String token);
}
