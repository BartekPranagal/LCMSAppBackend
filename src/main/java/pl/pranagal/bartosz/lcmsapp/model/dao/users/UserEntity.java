package pl.pranagal.bartosz.lcmsapp.model.dao.users;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atmosphere.config.service.Get;
import pl.pranagal.bartosz.lcmsapp.model.Idenficiable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements Idenficiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String mail;
    private String name;
    private String surname;


    @ManyToMany(cascade = CascadeType.MERGE)
    private List<AuthorityEntity> authorityEntityList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return mail != null ? mail.equals(that.mail) : that.mail == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }
}
