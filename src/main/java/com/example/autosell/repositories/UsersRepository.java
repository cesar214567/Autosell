package com.example.autosell.repositories;

import com.example.autosell.entities.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsersRepository extends PagingAndSortingRepository<Users,Long> {

    Users findByEmailAndPassword(String email, String password);

    Users findByEmail(String email);

}
