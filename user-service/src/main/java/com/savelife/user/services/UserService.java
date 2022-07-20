package com.savelife.user.services;

import com.savelife.user.entities.User;
import com.savelife.user.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private  UserService(UserRepository repository){
        this.repository = repository;
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public User findUser(@PathVariable Long id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new EntityNotFoundException("User Not Found!"));
    }

    public Page<User> listAllUsers(Pageable pageable){
        return repository.findAll(pageable);
    }

    public User updateUser(User updatedUser, Long id){
        User user = this.findUser(id);
        updatedUser.setId(user.getId());
        return repository.save(updatedUser);
    }

    public void deleteUser(@PathVariable Long id){
        User user = this.findUser(id);
        repository.delete(user);
    }

}
