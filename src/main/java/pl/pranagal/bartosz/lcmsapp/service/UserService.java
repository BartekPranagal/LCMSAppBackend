package pl.pranagal.bartosz.lcmsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pranagal.bartosz.lcmsapp.model.dao.users.AuthorityEntity;
import pl.pranagal.bartosz.lcmsapp.model.dao.users.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.user.UserRequest;
import pl.pranagal.bartosz.lcmsapp.repository.AuthorityRepository;
import pl.pranagal.bartosz.lcmsapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService  {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;


    public UserEntity saveUser(UserRequest userRequest){
        UserEntity user = new UserEntity();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setMail(userRequest.getMail());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setAuthorityEntityList(Arrays.asList(authorityRepository.findByAuthorityName("ROLE_USER").orElseThrow(()-> new RuntimeException())));
        //Why bother with setting everything up, when at the end you are throwing error?

        return userRepository.save(user);
    }





    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username));
        //Why dont use @ControllerAdvice to handle them all? :)

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                //.authorities(user.getAuthorityEntityList()
                       // .stream()
                      //  .map((AuthorityEntity::getAuthorityName))
                       // .toArray(String[]::new))
                .build();
    }
}
