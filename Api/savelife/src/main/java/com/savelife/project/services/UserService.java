package com.savelife.project.services;

import com.savelife.project.entities.Role;
import com.savelife.project.entities.UserModel;
import com.savelife.project.repositories.RoleRepository;
import com.savelife.project.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private  UserService(UserRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserModel saveUser(UserModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().forEach(role -> {
            Role roleExist = roleRepository.findByName(role.getName());
            if(roleExist != null){
                user.getRoles().set(user.getRoles().indexOf(role), roleExist);
            }
        });
        return repository.save(user);
    }

    public UserModel findUser(Long id){
        Optional<UserModel> user = repository.findById(id);
        return user.orElseThrow(() -> new EntityNotFoundException("User Not Found!"));
    }

    public UserModel findUserByEmail(String email){
        return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User Not Found!"));
    }

    public Page<UserModel> listAllUsers(Pageable pageable){
        return repository.findAll(pageable);
    }

    public UserModel updateUser(UserModel updatedUser, Long id){
        UserModel user = this.findUser(id);
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        updatedUser.setId(user.getId());
        if(updatedUser.getRoles() == null){
            updatedUser.setRoles(user.getRoles());
        }
        return repository.save(updatedUser);
    }

    public void deleteUser(@PathVariable Long id){
        UserModel user = this.findUser(id);
        repository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
}
