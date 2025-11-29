package radion.ru.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthentificationResponse {
    private String accessToken;
    private String refreshToken;
}
