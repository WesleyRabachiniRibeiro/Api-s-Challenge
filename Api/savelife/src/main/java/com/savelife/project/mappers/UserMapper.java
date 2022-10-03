package com.savelife.project.mappers;

import com.savelife.project.dto.user.RegistryUserDTO;
import com.savelife.project.dto.user.SearchUserDTO;
import com.savelife.project.dto.user.SearchUserEmail;
import com.savelife.project.entities.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserModel fromDTO(RegistryUserDTO dto){
        return new UserModel(null, dto.getName(), dto.getAge(), dto.getPhone(), dto.getEmail(), dto.getPassword(), dto.getPicture(), dto.getHealthPlan(), dto.getSusCard(), dto.getCpf(), dto.getRoles());
    }

    public static SearchUserDTO fromEntity(UserModel user){
        return new SearchUserDTO(user.getName(), user.getAge(), user.getPhone(), user.getEmail(), user.getPicture(), user.getHealthPlan(), user.getSusCard(), user.getCpf());
    }

    public static SearchUserEmail fromEntitytoUserEmail(UserModel user){
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> {
          roles.add(role.getName().toString());
        });
        return new SearchUserEmail(user.getId(), user.getName(), user.getAge(), user.getPhone(), user.getEmail(), user.getPicture(), user.getHealthPlan(), user.getSusCard(), user.getCpf(), roles);
    }

    public static List<SearchUserDTO> fromListEntity(List<UserModel> users){
        List<SearchUserDTO> searchUser = new ArrayList<>();
        users.forEach(user -> {
            searchUser.add(new SearchUserDTO(user.getName(), user.getAge(), user.getPhone(), user.getEmail(), user.getPicture(), user.getHealthPlan(), user.getSusCard(), user.getCpf()));
        });
        return searchUser;
    }
}
