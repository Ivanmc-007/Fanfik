package com.ivan.fanfik.service;

import com.ivan.fanfik.annotation.CurrentUser;
import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.repository.CompositionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

   public Optional<Composition> findById(Long id/* , Optional<User> optionalUser */) {
      // Optional<Composition> optional = compositionRepository.findById(id);
      // if (optional.isPresent() && optionalUser.isPresent()) {
      // Composition composition = optional.get();
      // composition.setUser(optionalUser.get());
      // return Optional.of(composition);
      // }
      // return optional;
      return compositionRepository.findById(id);
   }

}