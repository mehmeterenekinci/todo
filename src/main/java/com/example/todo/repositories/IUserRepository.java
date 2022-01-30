package com.example.todo.repositories;

import com.example.todo.entities.User;

public interface IUserRepository {

    User save(User user);

    User findByPhone(String phone);
}
