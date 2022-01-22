package com.example.autosell.utils.repositories;

import com.example.autosell.utils.entities.Accesorio;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AccesorioRepository extends PagingAndSortingRepository<Accesorio,Long> {

    List<Accesorio> findAllByEnabled(Boolean enabled);

}
