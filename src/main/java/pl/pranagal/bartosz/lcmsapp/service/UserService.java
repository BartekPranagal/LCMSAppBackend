package pl.pranagal.bartosz.lcmsapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
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
import pl.pranagal.bartosz.lcmsapp.model.dto.UserResponse;
import pl.pranagal.bartosz.lcmsapp.repository.AuthorityRepository;
import pl.pranagal.bartosz.lcmsapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService{

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    public UserEntity saveUser(UserRequest userRequest) throws RuntimeException {

        if(userNameExists(userRequest.getUsername()) || eMailExists(userRequest.getEmail())) {
            throw new RuntimeException("There is account with this username or email ");
        }

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
        return userRepository.findByEmail(email).isPresent();
    }


    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public UserResponse updateUser(Long id,UserRequest request){
        UserEntity existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setUsername(request.getUsername().isEmpty() ? existingUser.getUsername() : request.getUsername());
        existingUser.setPassword(request.getPassword().isEmpty() ? existingUser.getPassword() : request.getPassword());
        existingUser.setEmail(request.getEmail().isEmpty() ? existingUser.getEmail() : request.getEmail());
        existingUser.setName(request.getName().isEmpty() ? existingUser.getName() : request.getName());
        existingUser.setSurname(request.getSurname().isEmpty() ? existingUser.getSurname() : request.getSurname());

        return userMapper.entityToResponse(existingUser);

    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }




//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username).orElseThrow(
//                ()-> new UsernameNotFoundException("User with username not found"));
//    }





}
