package com.savelife.user.mappers;

import com.savelife.user.dto.user.RegistryUserDTO;
import com.savelife.user.dto.user.SearchUserDTO;
import com.savelife.user.entities.User;

public class UserMapper {

    public static User fromDTO(RegistryUserDTO dto){
        return new User(null, dto.getName(), dto.getAge(), dto.getPhone(), dto.getEmail(), dto.getPassword(), dto.getPicture(), dto.getHealthPlan(), dto.getSusCard(), dto.getCpf());
    }

    public static SearchUserDTO fromEntity(User user){
        return new SearchUserDTO(user.getId(), user.getName(), user.getAge(), user.getPhone(), user.getEmail(), user.getPicture(), user.getHealthPlan(), user.getSusCard(), user.getCpf());
    }
}
