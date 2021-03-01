package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.repository.CompositionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompositionService {
   private final CompositionRepository compositionRepository;

   public CompositionService(CompositionRepository compositionRepository) {
      this.compositionRepository = compositionRepository;
   }

   @Transactional(rollbackFor = { Exception.class })
   public Composition save(Composition composition) {
      return compositionRepository.save(composition);
   }

   @Transactional(readOnly = true)
   public List<Composition> findByUser(User user) {
      return compositionRepository.findByUser(user);
   }

   @Transactional(readOnly = true)
   public List<Composition> findAll() {
      return compositionRepository.findAll();
   }

   @Transactional(readOnly = true)
   public Optional<Composition> findById(Long id) {
      return compositionRepository.findById(id);
   }

   @Transactional(readOnly = true)
   public Optional<Composition> findByIdFast(Long id) {
      return compositionRepository.findByIdDefault(id);
   }

   @Transactional(readOnly = true)
   public List<Composition> findByNewestDateUpdate() {
      return compositionRepository.findTop15ByOrderByDateUpdateDesc();
   }

   @Transactional(readOnly = true)
   public List<Composition> findBySearchText(String text) {
      if (stringIsEmpty(text))
         return new ArrayList<>();
      return compositionRepository.findByNameOrTagText(text);
   }

   private boolean stringIsEmpty(String string) {
<<<<<<< HEAD
      return string == null || string.length() == 0;
=======
      return StringUtils.isEmpty(string);
>>>>>>> dev
   }

}