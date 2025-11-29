package radion.ru.userservice.service;

import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.entity.UserSchool;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserSchool getById(UUID uuid);
    UserSchool signUp(SignUpUserDto signUpUserDto);

    boolean signIn(SignInUserDto signInUserDto);

    UserSchool getByEmail(String subject);
}
