package radion.ru.userservice.config;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Base64;

@Configuration
public class JwtConfig {
    @Value("${jwt.secretKey.dev.key}")
    private String secretKeyString;
    @Getter
    @Value("${jwt.secretKey.dev.expiration_access_token}")
    private long expirationAccessToken;
    @Getter
    @Value("${jwt.secretKey.dev.expiration_refresh_token}")
    private long expirationRefreshToken;

    @Bean
    public SecretKey secretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyString);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
