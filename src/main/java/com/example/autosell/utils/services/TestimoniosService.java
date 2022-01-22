package com.example.autosell.utils.services;

import com.example.autosell.utils.entities.Testimonios;

public interface TestimoniosService {

    Testimonios save(Testimonios testimonios);

    void delete(Testimonios testimonios);

    Testimonios findById(Long id);

    Iterable<Testimonios> findAll();
}
