package radion.ru.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.userservice.dto.AuthentificationResponse;
import radion.ru.userservice.dto.SignInUserDto;
import radion.ru.userservice.dto.SignUpUserDto;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.exception.exceptions.BadPasswordException;
import radion.ru.userservice.jpa.UserJpaRepository;
import radion.ru.userservice.mapper.UserMapper;
import radion.ru.userservice.service.AuthService;
import radion.ru.userservice.service.TokenGenerateService;
import radion.ru.userservice.service.UserService;
import radion.ru.userservice.util.State;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final TokenGenerateService tokenGenerateService;
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public AuthentificationResponse signUp(SignUpUserDto signUpUserDto) {
        if(userJpaRepository.existsByEmail(signUpUserDto.getEmail())){
            throw new RuntimeException("User is already exist");
        }
        signUpUserDto.setState(State.DONT_STUDY);
        userJpaRepository.save(userMapper.mapToUser(signUpUserDto));

        return AuthentificationResponse.builder()
                .accessToken(tokenGenerateService.generateAccessToken(signUpUserDto.getEmail()))
                .refreshToken(tokenGenerateService.generateRefreshToken(
                        userService.getByEmail(signUpUserDto.getEmail())
                ))
                .build();
    }

    @Override
    public AuthentificationResponse signIn(SignInUserDto signInUserDto) {
        UserSchool user = userService.getByEmail(signInUserDto.getEmail());

        if (user.getPassword().equals(signInUserDto.getPassword())) {
            return AuthentificationResponse.builder()
                    .accessToken(tokenGenerateService.generateAccessToken(signInUserDto.getEmail()))
                    .refreshToken(tokenGenerateService.generateRefreshToken(user))
                    .build();
        }
        throw new BadPasswordException("Password is bad!");
    }
}
