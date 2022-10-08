package pl.pranagal.bartosz.lcmsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.AppExceptionResponse;
import pl.pranagal.bartosz.lcmsapp.model.dto.UserRequest;
import pl.pranagal.bartosz.lcmsapp.model.dto.UserResponse;
import pl.pranagal.bartosz.lcmsapp.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest user) {

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request, @PathVariable Long id){
        userService.updateUser(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
