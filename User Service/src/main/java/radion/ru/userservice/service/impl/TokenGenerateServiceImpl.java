package radion.ru.userservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import radion.ru.userservice.config.JwtConfig;
import radion.ru.userservice.dto.AuthentificationResponse;
import radion.ru.userservice.entity.RefreshTokenEntity;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.service.TokenGenerateService;
import radion.ru.userservice.service.TokenService;
import radion.ru.userservice.service.UserService;
import radion.ru.userservice.util.JwtUtil;

import java.util.*;


@Service
@Setter
@RequiredArgsConstructor
public class TokenGenerateServiceImpl implements TokenGenerateService {
    private final JwtConfig jwtConfig;
    private final TokenService tokenService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setClaims(
                        jwtUtil.claimsFill(email)
                )
                .setSubject(email)
                .setIssuer("your-app")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationAccessToken()))
                .signWith(jwtConfig.secretKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(UserSchool userSchool) {
        tokenService.deleteAllRefreshTokensByUser(userSchool);
        var expiryDate = new Date(System.currentTimeMillis() + jwtConfig.getExpirationRefreshToken());

        String refreshToken = Jwts.builder()
                .setSubject(userSchool.getEmail())
                .setIssuer("your-app")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(jwtConfig.secretKey())
                .compact();

        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
                .token(refreshToken)
                .user(userSchool)
                .expiryDate(expiryDate)
                .build();
        tokenService.saveRefreshToken(refreshTokenEntity);

        return refreshToken;
    }

    @Override
    public AuthentificationResponse reloadToken(String refreshToken) {
        String newRefreshToken = "";
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfig.secretKey())
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        var user = userService.getByEmail(claims.getSubject());

        if (jwtUtil.validateRefreshToken(refreshToken)) newRefreshToken = refreshToken;
        else newRefreshToken = generateRefreshToken(user);

        return AuthentificationResponse.builder()
                .accessToken(generateAccessToken(user.getEmail()))
                .refreshToken(newRefreshToken)
                .build();
    }
}
