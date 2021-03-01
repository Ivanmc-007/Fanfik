package com.ivan.fanfik.service;

import java.util.List;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Paragraph;
import com.ivan.fanfik.repository.ParagraphRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParagraphService {

   private final ParagraphRepository paragraphRepository;

   public ParagraphService(ParagraphRepository paragraphRepository) {
      this.paragraphRepository = paragraphRepository;
   }

   @Transactional(readOnly = true)
   public List<Paragraph> findByComposition(Composition composition) {
      return paragraphRepository.findByComposition(composition);
   }

   @Transactional(rollbackFor = { Exception.class })
   public Paragraph save(Paragraph paragraph) {
      return paragraphRepository.save(paragraph);
   }

}