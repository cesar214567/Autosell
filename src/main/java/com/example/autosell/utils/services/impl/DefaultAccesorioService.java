package com.example.autosell.utils.services.impl;

import com.example.autosell.utils.entities.Accesorio;
import com.example.autosell.utils.repositories.AccesorioRepository;
import com.example.autosell.utils.services.AccesorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAccesorioService implements AccesorioService {
    @Autowired
    AccesorioRepository accesorioRepository;

    @Override
    public List<Accesorio> getAllEnabled() {
        return accesorioRepository.findAllByEnabled(true);
    }

    @Override
    public List<Accesorio> getAll() {
        return (List<Accesorio>) accesorioRepository.findAll();
    }

    @Override
    public Accesorio save(Accesorio accesorio) {
        return accesorioRepository.save(accesorio);
    }

    @Override
    public Accesorio getById(Long id) {
        return accesorioRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean existById(Long id) {
        return accesorioRepository.existsById(id);
    }
}
