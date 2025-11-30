package radion.ru.userservice.dto;

import lombok.*;
import radion.ru.userservice.util.Role;
import radion.ru.userservice.util.State;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdate {
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String fullName;

    private Date birthday;
    private String phoneNumber;
    private State state;
    private Set<Role> roles;

    private Long drivingSchoolId;
}
