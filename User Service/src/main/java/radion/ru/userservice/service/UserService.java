package radion.ru.userservice.service;

import radion.ru.userservice.dto.UserResponse;
import radion.ru.userservice.dto.UserUpdate;
import radion.ru.userservice.entity.UserSchool;

import java.util.UUID;

public interface UserService {
    UserResponse getById(UUID uuid);
    UserSchool getByEmail(String subject);

    UserResponse updateUser(UserUpdate userSchool);
}
