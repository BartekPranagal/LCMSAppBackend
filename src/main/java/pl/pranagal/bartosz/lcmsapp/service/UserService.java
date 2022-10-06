package pl.pranagal.bartosz.lcmsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pranagal.bartosz.lcmsapp.configuration.mapper.UserMapper;
import pl.pranagal.bartosz.lcmsapp.model.dao.AuthorityEntity;
import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.UserRequest;
import pl.pranagal.bartosz.lcmsapp.repository.AuthorityRepository;
import pl.pranagal.bartosz.lcmsapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService  {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    public UserEntity saveUser(UserRequest userRequest) throws Exception {

        if(userNameExists(userRequest.getUsername()) || eMailExists(userRequest.getEmail())) {
            throw new Exception("There is account with this username or email ");
        }

//        if(emailAndUsernameExists(userRequest.getEmail(), userRequest.getUsername())){
//            throw  new Exception("There is account with this username or email");
//        }

        AuthorityEntity authorityEntity = authorityRepository.findByAuthorityName("ROLE_USER")
                .orElseThrow(RuntimeException::new);

        String password = passwordEncoder.encode(userRequest.getPassword());

        UserEntity user = userMapper.dtoToEntity(userRequest);
        user.setPassword(password);
        user.setAuthorityEntityList(List.of(authorityEntity));

        return userRepository.save(user);
    }

    private boolean userNameExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }
    private boolean eMailExists(String email){
        return userRepository.findAllByEmail(email).isPresent();
    }

    private boolean emailAndUsernameExists(String email, String username){
        return userRepository.findAllByEmailAndUsername(email,username).isPresent();
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
