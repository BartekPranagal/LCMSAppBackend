package pl.pranagal.bartosz.lcmsapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.LoginRequest;
import pl.pranagal.bartosz.lcmsapp.model.dto.LoginResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/login")
public class LoginController {


   private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity getJwt(@RequestBody LoginRequest loginRequest){
        try {

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()));

            UserEntity user = (UserEntity) authenticate.getPrincipal();

            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("Admin")
                    .sign(algorithm);

            LoginResponse loginResponse = new LoginResponse(user.getUsername(), token);
            return ResponseEntity.ok(loginResponse);

        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
