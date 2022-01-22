package com.example.autosell.services.impl;

import com.example.autosell.entities.Users;
import com.example.autosell.repositories.UsersRepository;
import com.example.autosell.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUsersService implements UsersService {

    @Autowired
    UsersRepository usersRepository;


    @Override
    public Users save(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public void delete(Users users) {
        usersRepository.delete(users);
    }

    @Override
    public Users getById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public List<Users> getAll() {
        return (List<Users>) usersRepository.findAll();
    }

    @Override
    public Users login(String email, String password) {
        return usersRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public Users getByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
