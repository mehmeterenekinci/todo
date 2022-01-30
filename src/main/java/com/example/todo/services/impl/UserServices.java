package com.example.todo.services.impl;

import com.example.todo.dto.GeneralResponse;
import com.example.todo.dto.UserDto;
import com.example.todo.entities.User;
import com.example.todo.mapper.UserMapper;
import com.example.todo.repositories.UserRepository;
import com.example.todo.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements IUserServices {

    UserRepository userRepository;
    private final UserMapper userMapper;
    private final GeneralResponse response;

    @Autowired
    public UserServices(UserMapper userMapper, GeneralResponse response) {
        this.userMapper = userMapper;
        this.response = response;
    }

    @Override
    public GeneralResponse saveUser(UserDto userDto) {
//        GeneralResponse response = new GeneralResponse();
        try {
            User user = userRepository.findByPhone(userDto.getPhone());
            if(user != null) {
                response.setCode(200);
                response.setSuccess(false);
                response.setMessage("User already exists.");
            }
            user = userRepository.save(userMapper.dtoToEntity(userDto));
            response.setCode(200);
            response.setSuccess(true);
            response.setData(userMapper.entityToDto(user));
            response.setMessage("User save is successful.");
        } catch (Exception e) {
            response.setCode(400);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public GeneralResponse logInUser(UserDto userDto) {
//        GeneralResponse response = new GeneralResponse();
        try {
            User user = userRepository.findByPhone(userDto.getPhone());
            response.setCode(200);
            if(user != null) {
                if(userDto.getPassword().equals(user.getPassword())){
                    response.setSuccess(true);
                    response.setData(userMapper.entityToDto(user));
                    response.setMessage("User found.");
                } else {
                    response.setSuccess(true);
                    response.setMessage("Phone or password is wrong!");
                }
            } else {
                response.setSuccess(false);
                response.setMessage("User not found!");
            }
        } catch (Exception e) {
            response.setCode(400);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
