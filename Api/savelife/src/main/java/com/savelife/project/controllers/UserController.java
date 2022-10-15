package com.savelife.project.controllers;

import com.savelife.project.dto.user.RegistryEmail;
import com.savelife.project.dto.user.RegistryUserDTO;
import com.savelife.project.dto.user.SearchUserDTO;
import com.savelife.project.dto.user.SearchUserEmail;
import com.savelife.project.mappers.UserMapper;
import com.savelife.project.entities.UserModel;
import com.savelife.project.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;


@RestController
@RequestMapping("/v1/user")
@Api(value = "API REST Usuário")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Create new Users")
    public ResponseEntity<SearchUserDTO> saveUser(@RequestBody RegistryUserDTO dto){
        try{
            UserModel user = service.saveUser(UserMapper.fromDTO(dto));
            return new ResponseEntity<SearchUserDTO>(UserMapper.fromEntity(user), HttpStatus.CREATED);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/email")
    @ApiOperation(value = "Return user to email")
    public ResponseEntity<SearchUserEmail> findUserbyEmail(@RequestBody RegistryEmail dto){
        try {
            UserModel user = service.findUserByEmail(dto.getEmail());
            return ResponseEntity.ok(UserMapper.fromEntitytoUserEmail(user));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
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
            UserModel user = service.findUser(id);
            return ResponseEntity.ok(UserMapper.fromEntity(user));
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    @ApiOperation(value = "Update User to ID")
    public ResponseEntity<SearchUserDTO> updateUser(@RequestBody RegistryUserDTO dto, @PathVariable Long id){
        try{
            UserModel user = service.updateUser(UserMapper.fromDTO(dto), id);
            return ResponseEntity.ok(UserMapper.fromEntity(user));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete User to ID")
    public ResponseEntity<SearchUserDTO> deleteUser(@PathVariable Long id){
        try{
            service.deleteUser(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
