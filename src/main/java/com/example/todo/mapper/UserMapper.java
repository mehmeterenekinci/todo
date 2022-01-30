package com.example.todo.mapper;

import com.example.todo.dto.UserDto;
import com.example.todo.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto entityToDto(User user){
        return UserDto.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                .email(user.getEmail()).phone(user.getPhone()).password(user.getPassword()).build();
    }

    public User dtoToEntity(UserDto dto){
        return User.builder().firstName(dto.getFirstName()).lastName(dto.getLastName())
                .email(dto.getEmail()).phone(dto.getPhone()).password(dto.getPassword()).build();
    }

}
