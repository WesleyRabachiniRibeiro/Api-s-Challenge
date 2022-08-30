package com.savelife.project.mappers;

import com.savelife.project.dto.user.RegistryUserDTO;
import com.savelife.project.dto.user.SearchUserDTO;
import com.savelife.project.entities.User;

public class UserMapper {

    public static User fromDTO(RegistryUserDTO dto){
        return new User(null, dto.getName(), dto.getAge(), dto.getPhone(), dto.getEmail(), dto.getPassword(), dto.getPicture(), dto.getHealthPlan(), dto.getSusCard(), dto.getCpf());
    }

    public static SearchUserDTO fromEntity(User user){
        return new SearchUserDTO(user.getName(), user.getAge(), user.getPhone(), user.getEmail(), user.getPicture(), user.getHealthPlan(), user.getSusCard(), user.getCpf());
    }
}
