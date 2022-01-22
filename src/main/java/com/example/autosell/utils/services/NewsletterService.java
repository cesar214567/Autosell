package com.example.autosell.utils.services;

import com.example.autosell.utils.entities.Newsletter;

import java.util.List;

public interface NewsletterService {
    Newsletter save(Newsletter newsletter);

    void delete(Newsletter newsletter);

    Newsletter findById(Long id);

    List<Newsletter> findAll();

}

