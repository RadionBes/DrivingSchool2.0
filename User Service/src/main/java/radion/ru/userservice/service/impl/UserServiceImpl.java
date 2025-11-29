package radion.ru.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.exception.exceptions.UserNotFoundException;
import radion.ru.userservice.jpa.UserJpaRepository;
import radion.ru.userservice.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;


    @Override
    public UserSchool getByEmail(String subject) {
        return userJpaRepository.findByEmail(subject).orElseThrow(() ->
                new UserNotFoundException("User is email=%s not found".formatted(subject))
        );
    }

    @Override
    public UserSchool getById(UUID uuid) {
        return userJpaRepository.findById(uuid)
                .orElseThrow(() ->
                        new UserNotFoundException("User id = %s not found!".formatted(uuid))
                );
    }
}
