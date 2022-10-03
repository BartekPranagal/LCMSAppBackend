package pl.pranagal.bartosz.lcmsapp.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
//Why don't you validate any of the fields, especially when you use builder later on
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String mail;
    private String name;
    private String surname;
}
