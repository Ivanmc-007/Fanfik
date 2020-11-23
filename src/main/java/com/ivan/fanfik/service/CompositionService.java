package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.repository.CompositionRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompositionService {
   private final CompositionRepository compositionRepository;

   public CompositionService(CompositionRepository compositionRepository) {
      this.compositionRepository = compositionRepository;
   }

   public Composition save(Composition composition) {
      return compositionRepository.save(composition);
   }

   public List<Composition> findByUser(User user) {
      return compositionRepository.findByUser(user);
   }

   public List<Composition> findAll() {
      return compositionRepository.findAll();
   }

   public Optional<Composition> findById(Long id) {
      return compositionRepository.findById(id);
   }

   public Optional<Composition> findByIdFast(Long id) {
      return compositionRepository.findByIdDefault(id);
   }

   public List<Composition> findByNewestDateUpdate() {
      return compositionRepository.findTop15ByOrderByDateUpdateDesc();
   }

   public List<Composition> findBySearchText(String text) {
      if (stringIsEmpty(text))
         return new ArrayList<>();
      return compositionRepository.findByNameOrTagText(text);
   };

   private boolean stringIsEmpty(String string) {
      return (string == null || string.length() == 0) ? true : false;
   }

}