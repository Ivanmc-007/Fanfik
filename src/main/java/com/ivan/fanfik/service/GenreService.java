package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Genre;
<<<<<<< HEAD
=======
import com.ivan.fanfik.repository.GenreRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> dev

import java.util.List;
import java.util.Set;

public interface GenreService {

<<<<<<< HEAD
    List<Genre> findAll();

    Set<Genre> findAllById(List<Long> ids);
=======
   @Transactional(readOnly = true)
   public List<Genre> findAll() {
      return genreRepository.findAll();
   }

   @Transactional(readOnly = true)
   public Set<Genre> findAllById(List<Long> ids) {
      return new HashSet<>(genreRepository.findAllById(ids));
   }
>>>>>>> dev

}
