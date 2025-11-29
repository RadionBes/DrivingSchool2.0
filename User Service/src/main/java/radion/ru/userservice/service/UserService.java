package radion.ru.userservice.service;

import radion.ru.userservice.entity.UserSchool;

import java.util.UUID;

public interface UserService {
    UserSchool getById(UUID uuid);
    UserSchool getByEmail(String subject);
}
