package pl.pranagal.bartosz.lcmsapp.model.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
