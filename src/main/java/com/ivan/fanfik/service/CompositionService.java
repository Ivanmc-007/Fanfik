package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.User;

import java.util.List;
import java.util.Optional;

public interface CompositionService {

   Composition save(Composition composition);

   List<Composition> findByUser(User user);

   List<Composition> findAll();

   Optional<Composition> findById(Long id);

   Optional<Composition> findByIdFast(Long id);

   List<Composition> findByNewestDateUpdate();

   List<Composition> findBySearchText(String text);

}