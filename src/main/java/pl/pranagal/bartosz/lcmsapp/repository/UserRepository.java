package pl.pranagal.bartosz.lcmsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pranagal.bartosz.lcmsapp.model.dao.users.UserEntity;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
