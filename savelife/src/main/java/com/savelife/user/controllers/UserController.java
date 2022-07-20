package com.savelife.user.controllers;

import com.savelife.user.dto.user.RegistryUserDTO;
import com.savelife.user.dto.user.SearchUserDTO;
import com.savelife.user.dto.user.UserMapper;
import com.savelife.user.entities.User;
import com.savelife.user.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("v1/user")
public class UserController {
    private final UserService service;

    private UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SearchUserDTO> saveUser(@RequestBody RegistryUserDTO dto){
        User user = service.saveUser(UserMapper.fromDTO(dto));
        return ResponseEntity.ok(UserMapper.fromEntity(user));
    }

    @GetMapping
    public ResponseEntity<Page<SearchUserDTO>> findAllUsers(@PathVariable Pageable pageable){
        return ResponseEntity.ok(service.listAllUsers(pageable).map(UserMapper::fromEntity));
    }
}
