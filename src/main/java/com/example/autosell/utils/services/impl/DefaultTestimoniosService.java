package com.example.autosell.utils.services.impl;

import com.example.autosell.utils.entities.Testimonios;
import com.example.autosell.utils.repositories.TestimoniosRepository;
import com.example.autosell.utils.services.TestimoniosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTestimoniosService  implements TestimoniosService {

    @Autowired
    TestimoniosRepository testimoniosRepository;

    @Override
    public Testimonios save(Testimonios testimonios) {
        return testimoniosRepository.save(testimonios);
    }

    @Override
    public void delete(Testimonios testimonios) {
        testimoniosRepository.delete(testimonios);
    }

    @Override
    public Testimonios findById(Long id) {
        return testimoniosRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Testimonios> findAll() {
        return testimoniosRepository.findAll();
    }
}
