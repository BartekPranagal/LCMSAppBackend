package pl.pranagal.bartosz.lcmsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pranagal.bartosz.lcmsapp.model.dao.AuthorityEntity;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity,Long> {
    Optional<AuthorityEntity> findByAuthorityName(String name);
}
