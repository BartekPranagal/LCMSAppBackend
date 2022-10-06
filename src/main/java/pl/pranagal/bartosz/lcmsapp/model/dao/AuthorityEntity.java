package pl.pranagal.bartosz.lcmsapp.model.dao;

import lombok.*;
import pl.pranagal.bartosz.lcmsapp.model.Idenficiable;
import javax.persistence.*;


@Entity
@Table(name = "authorities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuthorityEntity implements Idenficiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "AuthorityName")
    private String authorityName;
}
