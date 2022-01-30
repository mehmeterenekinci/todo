package com.example.todo.services;

import com.example.todo.dto.GeneralResponse;
import com.example.todo.dto.UserDto;

public interface IUserServices {

    GeneralResponse saveUser(UserDto userDto);

    GeneralResponse logInUser(UserDto userDto);

}
