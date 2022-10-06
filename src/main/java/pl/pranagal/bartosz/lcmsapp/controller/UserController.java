package pl.pranagal.bartosz.lcmsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.AppExceptionResponse;
import pl.pranagal.bartosz.lcmsapp.model.dto.UserRequest;
import pl.pranagal.bartosz.lcmsapp.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody @Valid UserRequest user) {

        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
