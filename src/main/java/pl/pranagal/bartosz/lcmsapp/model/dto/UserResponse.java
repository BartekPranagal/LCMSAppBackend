package pl.pranagal.bartosz.lcmsapp.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private String mail;
    private String name;
    private String surname;
}
