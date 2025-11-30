package radion.ru.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import radion.ru.userservice.util.Role;
import radion.ru.userservice.util.State;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSchool {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,  nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String patronymic;
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false, length = 11)
    private String phoneNumber;
    @Column(nullable = false)
    private State state;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"
            )
    )
    @Column(name = "role")
    private Set<Role> roles;

    private Long drivingSchoolId;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
