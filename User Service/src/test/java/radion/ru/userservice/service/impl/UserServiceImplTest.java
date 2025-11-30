package radion.ru.userservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import radion.ru.userservice.dto.UserResponse;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.exception.exceptions.UserNotFoundException;
import radion.ru.userservice.jpa.UserJpaRepository;
import radion.ru.userservice.util.Role;
import radion.ru.userservice.util.State;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserJpaRepository userJpaRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserSchool userSchool;
    private UUID userId;

    @BeforeEach
    void setUp() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        userId = UUID.randomUUID();

        userSchool = UserSchool.builder()
                .id(userId)
                .birthday(new Date())
                .drivingSchoolId(1L)
                .email("test@gmail.com")
                .roles(Set.of(Role.ROLE_STUDENT))
                .state(State.DONT_STUDY)
                .fullName("Radion Besalneev Believ")
                .name("Radion")
                .password(passwordEncoder.encode("1234"))
                .surname("Beslaneev")
                .phoneNumber("89990004545")
                .patronymic("Believ")
                .build();
    }

    @Test
    void getByEmail_ShouldReturnUser_WhenUserExists() {
        // Arrange
        String email = "test@gmail.com";
        when(userJpaRepository.findByEmail(email)).thenReturn(Optional.of(userSchool));

        // Act
        UserSchool result = userServiceImpl.getByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(userSchool.getId(), result.getId());
        assertEquals(userSchool.getEmail(), result.getEmail());
        assertEquals(userSchool.getFullName(), result.getFullName());
        verify(userJpaRepository, times(1)).findByEmail(email);
    }

    @Test
    void getByEmail_ShouldThrowUserNotFoundException_WhenUserNotFound() {
        // Arrange
        String email = "nonexistent@gmail.com";
        when(userJpaRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () ->
                userServiceImpl.getByEmail(email)
        );
        verify(userJpaRepository, times(1)).findByEmail(email);
    }

    @Test
    void getById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userJpaRepository.findById(userId)).thenReturn(Optional.of(userSchool));

        // Act
        UserResponse result = userServiceImpl.getById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userSchool.getId(), result.getId());
        assertEquals(userSchool.getEmail(), result.getEmail());
        assertEquals(userSchool.getFullName(), result.getFullName());
        verify(userJpaRepository, times(1)).findById(userId);
    }

    @Test
    void getById_ShouldThrowUserNotFoundException_WhenUserNotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(userJpaRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () ->
                userServiceImpl.getById(nonExistentId)
        );
        verify(userJpaRepository, times(1)).findById(nonExistentId);
    }

    @Test
    void getByEmail_ShouldUseCorrectEmailFormat() {
        // Arrange
        String emailWithSpecialChars = "test.user+tag@example.com";
        UserSchool specialUser = UserSchool.builder()
                .id(UUID.randomUUID())
                .email(emailWithSpecialChars)
                .password("password")
                .surname("Doe")
                .name("John")
                .patronymic("Smith")
                .fullName("John Doe Smith")
                .birthday(new Date())
                .phoneNumber("89990001234")
                .state(State.DONT_STUDY)
                .roles(Set.of(Role.ROLE_STUDENT))
                .build();

        when(userJpaRepository.findByEmail(emailWithSpecialChars)).thenReturn(Optional.of(specialUser));

        // Act
        UserSchool result = userServiceImpl.getByEmail(emailWithSpecialChars);

        // Assert
        assertNotNull(result);
        assertEquals(emailWithSpecialChars, result.getEmail());
        verify(userJpaRepository, times(1)).findByEmail(emailWithSpecialChars);
    }

    @Test
    void getByEmail_ShouldHandleEmptyEmail() {
        // Arrange
        String emptyEmail = "";
        when(userJpaRepository.findByEmail(emptyEmail)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () ->
                userServiceImpl.getByEmail(emptyEmail)
        );
    }

    @Test
    void getByEmail_ShouldHandleNullEmail() {
        // Arrange
        when(userJpaRepository.findByEmail(null)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () ->
                userServiceImpl.getByEmail(null)
        );
    }

    @Test
    void getById_ShouldVerifyRepositoryInteraction() {
        // Arrange
        when(userJpaRepository.findById(userId)).thenReturn(Optional.of(userSchool));

        // Act
        userServiceImpl.getById(userId);

        // Assert - проверяем, что метод репозитория был вызван ровно один раз с правильным аргументом
        verify(userJpaRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userJpaRepository);
    }
}