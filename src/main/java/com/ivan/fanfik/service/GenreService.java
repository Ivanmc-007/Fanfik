package com.ivan.fanfik.service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

import com.ivan.fanfik.entity.Genre;
import com.ivan.fanfik.repository.GenreRepository;

import org.springframework.stereotype.Service;

@Service
public class GenreService {

   private final GenreRepository genreRepository;

   public GenreService(GenreRepository genreRepository) {
      this.genreRepository = genreRepository;
   }

   public List<Genre> findAll() {
      return genreRepository.findAll();
   }

   public Set<Genre> findAllById(List<Long> ids) {
      return new HashSet<>(genreRepository.findAllById(ids));
   }

}