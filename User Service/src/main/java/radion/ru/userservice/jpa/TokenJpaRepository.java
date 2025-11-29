package radion.ru.userservice.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.userservice.entity.RefreshTokenEntity;
import radion.ru.userservice.entity.UserSchool;

public interface TokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {
    void deleteAllByUser(UserSchool user);

    boolean existsByToken(String token);
}
