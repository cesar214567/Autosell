package com.example.autosell.utils.services;

import com.example.autosell.utils.entities.Accesorio;

import java.util.List;

public interface AccesorioService {

    List<Accesorio> getAllEnabled();

    List<Accesorio> getAll();   

    Accesorio save(Accesorio accesorio);

    Accesorio getById(Long id);

    Boolean existById(Long id);


}
