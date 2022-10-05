package pl.pranagal.bartosz.lcmsapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.LoginRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/login")
public class LoginController {


   private final AuthenticationManager authenticationManager;

    @PostMapping
    public String getJwt(@RequestBody LoginRequest loginRequest){

       Authentication authenticate =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));

        UserEntity user = (UserEntity) authenticate.getPrincipal();

        System.out.println(user.toString());
        return "";
    }
}
