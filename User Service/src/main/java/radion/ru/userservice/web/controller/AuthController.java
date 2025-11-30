package radion.ru.userservice.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.userservice.dto.AuthentificationResponse;
import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.dto.UserResponse;
import radion.ru.userservice.service.AuthService;
import radion.ru.userservice.service.TokenGenerateService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenGenerateService tokenGenerateService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthentificationResponse> signUp(@RequestBody @Valid SignUpUserDto signUpUserDto){
        return ResponseEntity.ok(authService.signUp(signUpUserDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthentificationResponse> signIn(@RequestBody SignInUserDto signInUserDto){
        return ResponseEntity.ok(
                authService.signIn(signInUserDto)
        );
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthentificationResponse> tokensUpdate(@RequestParam String token){
        return ResponseEntity.ok(tokenGenerateService.reloadToken(token));
    }
}
