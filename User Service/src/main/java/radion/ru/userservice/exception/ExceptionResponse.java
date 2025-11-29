package radion.ru.userservice.exception;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ExceptionResponse {
    @NotNull
    @Builder.Default
    private int httpStatus = 500;

    @NotBlank
    @Builder.Default
    private String message = "Internal server error";
}
