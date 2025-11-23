package radion.ru.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import radion.ru.userservice.util.State;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserDto {

    @Email
    private String email;
    @Size(min = 10, max = 50)
    private String password;
    @NotBlank
    private String surname;

    @NotBlank
    private String name;
    @NotBlank
    private String patronymic;
    @NotBlank
    private String fullName;

    @NotNull
    private Date birthday;
    @NotBlank
    private String phoneNumber;
    private State state;
}
