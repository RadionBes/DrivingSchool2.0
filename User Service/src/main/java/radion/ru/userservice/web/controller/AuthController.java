package radion.ru.userservice.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.userservice.dto.AuthentificationResponse;
import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthentificationResponse> signUp(@RequestBody @Valid SignUpUserDto signUpUserDto){
        return ResponseEntity.ok(authService.signUp(signUpUserDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthentificationResponse> signIn(@RequestBody SignInUserDto signInUserDto){
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .body(authService
                        .signIn(signInUserDto)
                );
    }
}
