package com.example.autosell.utils.repositories;

import com.example.autosell.utils.entities.Newsletter;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsletterRepository extends PagingAndSortingRepository<Newsletter,Long> {
}
