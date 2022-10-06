package pl.pranagal.bartosz.lcmsapp.model.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
//Why don't you validate any of the fields, especially when you use builder later on
    @NotBlank
    @NonNull
    private String username;
    @NotBlank
    @NonNull
    private String password;
    @NotBlank
    @NonNull
    private String email;
    private String name;
    private String surname;
}
