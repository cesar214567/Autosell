package com.example.autosell.utils.services;

import com.example.autosell.utils.entities.FotoBanner;

import java.util.List;

public interface FotoBannerService {

    FotoBanner save(FotoBanner fotoBanner);

    void delete(FotoBanner fotoBanner);

    List<FotoBanner> findAll();
}
