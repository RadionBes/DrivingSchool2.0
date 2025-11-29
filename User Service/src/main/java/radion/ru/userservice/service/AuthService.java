package radion.ru.userservice.service;

import radion.ru.userservice.dto.AuthentificationResponse;
import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;

public interface AuthService {
    AuthentificationResponse signUp(SignUpUserDto signUpUserDto);
    AuthentificationResponse signIn(SignInUserDto signInUserDto);
}
