package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Genre;
import com.ivan.fanfik.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Set<Genre> findAllById(List<Long> ids) {
        return new HashSet<>(genreRepository.findAllById(ids));
    }

}

