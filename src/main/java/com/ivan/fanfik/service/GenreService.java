package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Genre;

import java.util.List;
import java.util.Set;

public interface GenreService {

   List<Genre> findAll();

   Set<Genre> findAllById(List<Long> ids);

}
