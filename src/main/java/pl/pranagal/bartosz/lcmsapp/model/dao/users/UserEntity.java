package pl.pranagal.bartosz.lcmsapp.model.dao.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
}
