package com.savelife.user.controllers;

import com.savelife.user.dto.user.RegistryUserDTO;
import com.savelife.user.dto.user.SearchUserDTO;
import com.savelife.user.dto.user.UserMapper;
import com.savelife.user.entities.User;
import com.savelife.user.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("v1/user")
@Api(value = "API REST Usuário")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService service;

    private UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Create new Users")
    public ResponseEntity<SearchUserDTO> saveUser(@RequestBody RegistryUserDTO dto){
        User user = service.saveUser(UserMapper.fromDTO(dto));
        return new ResponseEntity<SearchUserDTO>(UserMapper.fromEntity(user), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Return all registered users")
    public ResponseEntity<Page<SearchUserDTO>> findAllUsers(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(service.listAllUsers(pageable).map(UserMapper::fromEntity));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Return a User to ID")
    public ResponseEntity<SearchUserDTO> searchUser(@PathVariable Long id){
        try{
            User user = service.findUser(id);
            return ResponseEntity.ok(UserMapper.fromEntity(user));
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update User to ID")
    public ResponseEntity<SearchUserDTO> updateUser(@RequestBody RegistryUserDTO dto, @PathVariable Long id){
        try{
            User user = service.updateUser(UserMapper.fromDTO(dto), id);
            return ResponseEntity.ok(UserMapper.fromEntity(user));
        }catch (RuntimeException ex){
            ex.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete User to ID")
    public ResponseEntity<SearchUserDTO> deleteUser(@PathVariable Long id){
        try{
            service.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
