package com.example.autosell.controllers;

import com.example.autosell.Setup;
import com.example.autosell.entities.Users;
import com.example.autosell.services.UsersService;
import com.example.autosell.utils.errors.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = {Setup.local/*,Setup.route*/}, allowedHeaders = "*")
public class AuthController {


    @Autowired
    UsersService usersService;

    @PostMapping(value = "/login")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> login(@RequestBody Users user)  {
        Users users = usersService.login(user.getEmail(),user.getPassword());
        if(users ==null){
                return ResponseService.genError("no se encontro el user",HttpStatus.BAD_REQUEST);
        }else {
            return ResponseService.genSuccess(users);
        }
    }

    @PostMapping(value = "/register")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> register(@RequestBody Users user)  {
        return ResponseService.genSuccess(usersService.save(user));
    }



}
