package radion.ru.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.entity.UserSchool;
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
    public UserSchool getById(UUID uuid) {
        return userJpaRepository.findById(uuid)
                .orElseThrow(() ->
                        new RuntimeException("User id = %s not found!".formatted(uuid))
                );
    }
}
