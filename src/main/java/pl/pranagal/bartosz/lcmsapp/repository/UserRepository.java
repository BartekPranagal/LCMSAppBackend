package pl.pranagal.bartosz.lcmsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findAllByUsername(String username);

    Optional<UserEntity> findAllByEmailAndUsername(String email, String username);

    Optional<User> findByEmail(String email);

}
