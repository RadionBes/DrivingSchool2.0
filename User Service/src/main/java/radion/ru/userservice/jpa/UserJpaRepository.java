package radion.ru.userservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.userservice.entity.UserSchool;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserSchool, UUID> {

    boolean existsByEmail(String email);

    Optional<UserSchool> findByEmail(String email);
}
