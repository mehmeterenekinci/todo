package com.example.todo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
}