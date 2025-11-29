package radion.ru.userservice.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserSchool> signUp(@RequestBody SignUpUserDto signUpUserDto){
        return ResponseEntity.ok(userService.signUp(signUpUserDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInUserDto signInUserDto){
        return ResponseEntity.ok(userService.signIn(signInUserDto));
    }
}
