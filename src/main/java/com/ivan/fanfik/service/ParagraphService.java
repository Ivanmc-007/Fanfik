package com.ivan.fanfik.service;

import java.util.List;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Paragraph;
import com.ivan.fanfik.repository.ParagraphRepository;

import org.springframework.stereotype.Service;

@Service
public class ParagraphService {

   private final ParagraphRepository paragraphRepository;

   public ParagraphService(ParagraphRepository paragraphRepository) {
      this.paragraphRepository = paragraphRepository;
   }

   public List<Paragraph> findByComposition(Composition composition) {
      return paragraphRepository.findByComposition(composition);
   }

   public Paragraph save(Paragraph paragraph) {
      return paragraphRepository.save(paragraph);
   }

}