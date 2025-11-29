package radion.ru.userservice.service;

import org.jspecify.annotations.Nullable;
import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.entity.UserSchool;

import java.util.UUID;

public interface UserService {
    UserSchool getById(UUID uuid);
    UserSchool signUp(SignUpUserDto signUpUserDto);

    String signIn(SignInUserDto signInUserDto);
}
