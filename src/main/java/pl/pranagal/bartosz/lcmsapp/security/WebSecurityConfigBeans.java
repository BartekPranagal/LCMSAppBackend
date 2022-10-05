package pl.pranagal.bartosz.lcmsapp.security;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pranagal.bartosz.lcmsapp.repository.UserRepository;


@Configuration
@RequiredArgsConstructor
public class WebSecurityConfigBeans {

    private final UserRepository userRepo;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepo.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User with Email not found"));
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}