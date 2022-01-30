package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;

    @Min(value = 4 , message = "Password has to be longer then 4 characters!")
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phone;
}
