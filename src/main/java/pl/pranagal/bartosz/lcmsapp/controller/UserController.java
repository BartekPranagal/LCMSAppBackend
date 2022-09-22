package pl.pranagal.bartosz.lcmsapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pranagal.bartosz.lcmsapp.model.dao.users.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.user.UserRequest;
import pl.pranagal.bartosz.lcmsapp.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody @Valid UserRequest user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    //Why does it just returns entity without response code? How could you achieve that?

}
