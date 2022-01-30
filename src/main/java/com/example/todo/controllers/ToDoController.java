package com.example.todo.controllers;

import com.example.todo.dto.GeneralResponse;
import com.example.todo.dto.UserDto;
import com.example.todo.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/todo/api")
public class ToDoController {

    private final IUserServices userServices;

    @Autowired
    public ToDoController(IUserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping(value = "/saveUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse saveUser(@RequestBody @Valid UserDto userDto){
        return userServices.saveUser(userDto);
    }

    @PostMapping(value = "/logIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse logIn(@RequestBody @Valid UserDto userDto){
        return userServices.logInUser(userDto);
    }

    @PostMapping(value = "/createEvent", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse createEvent(@RequestBody @Valid UserDto userDto){
        return userServices.logInUser(userDto);
    }

}
