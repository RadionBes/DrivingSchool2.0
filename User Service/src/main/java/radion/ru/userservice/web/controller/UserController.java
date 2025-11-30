package radion.ru.userservice.web.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.userservice.dto.UserResponse;
import radion.ru.userservice.dto.UserUpdate;
import radion.ru.userservice.mapper.UserMapper;
import radion.ru.userservice.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/internal")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/id")
    public ResponseEntity<UserResponse> getById(@RequestParam("uuid") @NotBlank UUID uuid){
        return ResponseEntity.ok(
                userService.getById(uuid)
        );
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponse> getByEmail(@RequestParam("email") @NotBlank String uuid){
        return ResponseEntity.ok(
                userMapper.mapToUserResponse(
                        userService.getByEmail(uuid)
                )
        );
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdate user){
        return ResponseEntity.ok(
                userService.updateUser(user)
        );
    }
}
