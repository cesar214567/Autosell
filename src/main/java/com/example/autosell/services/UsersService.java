package com.example.autosell.services;

import com.example.autosell.entities.Users;

import java.util.List;

public interface UsersService {

    Users save(Users users);

    void delete(Users users);

    Users getById(Long id );

    List<Users> getAll();

    Users login(String email, String password);

    Users getByEmail(String email);
}
