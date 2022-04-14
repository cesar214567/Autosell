package com.example.autosell;

import com.example.autosell.entities.Users;
import com.example.autosell.services.UsersService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AutosellApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AutosellApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return  builder.sources(AutosellApplication.class);
    }
}


