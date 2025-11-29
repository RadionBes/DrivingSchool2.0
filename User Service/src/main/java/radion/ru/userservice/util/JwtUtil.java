package radion.ru.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import radion.ru.userservice.service.TokenService;
import radion.ru.userservice.service.UserService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secretKey.dev.key}")
    private SecretKey secretKey;
    private final TokenService tokenService;
    private final UserService userService;

    // Валидация Access токена
    public boolean validateAccessToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims
                    .getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Валидация Refresh токена
    public boolean validateRefreshToken(String token) {
        try {
            // Проверяем подпись и срок действия
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return tokenService
                    .ifPresentRefreshToken(token) && claims
                    .getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Object> claimsFill(String email){
        var user = userService.getByEmail(email);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId().toString());
        claims.put("surname", user.getSurname());
        claims.put("name", user.getName());
        claims.put("patronymic", user.getPatronymic());
        claims.put("fullName", user.getFullName());
        claims.put("birthday", user.getBirthday().getTime()); // Сохраняем как timestamp
        claims.put("phoneNumber", user.getPhoneNumber());
        claims.put("state", user.getState().name());
        return claims;
    }
}
