package pl.pranagal.bartosz.lcmsapp.model.dto;

import lombok.Data;
import pl.pranagal.bartosz.lcmsapp.model.dao.AuthorityEntity;

import java.util.List;

@Data
public class LoginResponse {

    private Long id;
    private String username;
    private String email;
    private List<AuthorityEntity> role;
    private String accessToken;


    public LoginResponse(Long id, String username, String email, List<AuthorityEntity> role, String accessToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.accessToken = accessToken;
    }
}
