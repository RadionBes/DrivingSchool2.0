package radion.ru.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.exception.exceptions.BadPasswordException;
import radion.ru.userservice.exception.exceptions.UserNotFoundException;
import radion.ru.userservice.jpa.UserJpaRepository;
import radion.ru.userservice.mapper.UserMapper;
import radion.ru.userservice.service.UserService;
import radion.ru.userservice.util.State;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public UserSchool signUp(SignUpUserDto signUpUserDto) {
        if(userJpaRepository.existsByEmail(signUpUserDto.getEmail())){
            throw new RuntimeException("User is already exist");
        }
        signUpUserDto.setState(State.EXPELLED);
        return userJpaRepository.save(
                userMapper.mapToUser(signUpUserDto)
        );
    }

    @Override
    public boolean signIn(SignInUserDto signInUserDto) {
        UserSchool user = userJpaRepository.findByEmail(signInUserDto.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User is email=%s not found".formatted(signInUserDto.getEmail()))
                );
        if (user.getPassword().equals(signInUserDto.getPassword())) return true;
        throw new BadPasswordException("Password is bad!");
    }

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
