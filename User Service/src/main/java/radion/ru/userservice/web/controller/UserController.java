package radion.ru.userservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.userservice.entity.UserSchool;
import radion.ru.userservice.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserSchool> getById(@RequestParam("uuid") UUID uuid){
        return ResponseEntity.ok(
            userService.getById(uuid)
        );
    }
}
