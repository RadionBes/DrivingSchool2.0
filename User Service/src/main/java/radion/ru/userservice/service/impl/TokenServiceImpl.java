package radion.ru.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.userservice.entity.RefreshTokenEntity;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.jpa.TokenJpaRepository;
import radion.ru.userservice.service.TokenService;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenJpaRepository tokenJpaRepository;

    @Override
    public void saveRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        tokenJpaRepository.save(refreshTokenEntity);
    }

    @Override
    public void deleteAllRefreshTokensByUser(UserSchool userSchool) {
        tokenJpaRepository.deleteAllByUser(userSchool);
    }

    @Override
    public boolean ifPresentRefreshToken(String token) {
        return tokenJpaRepository.existsByToken(token);
    }
}
