package com.ivan.fanfik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Paragraph;
import com.ivan.fanfik.entity.Views;
import com.ivan.fanfik.exception.CompositionNotFoundException;
import com.ivan.fanfik.service.CompositionService;
import com.ivan.fanfik.service.ParagraphService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paragraphs")
public class ParagraphRESTController {

   private final ParagraphService paragraphService;

   private final CompositionService compositionService;

   public ParagraphRESTController(ParagraphService paragraphService, CompositionService compositionService) {
      this.paragraphService = paragraphService;
      this.compositionService = compositionService;
   }

   @JsonView({ Views.Paragraph.class })
   @GetMapping("/compositions/{id}")
   public List<Paragraph> getByComposition(@PathVariable("id") Long id) {
      Optional<Composition> optional = compositionService.findById(id);
      if (optional.isPresent()) {
         return paragraphService.findByComposition(optional.get());
      }

      return new ArrayList<>();
   }

   @PostMapping("/compositions/{id}")
   public Paragraph addParagraph(@RequestBody Paragraph paragraph, @PathVariable("id") Long id) {
      Optional<Composition> optional = compositionService.findById(id);
      if (optional.isPresent()) {
         Composition composition = optional.get();
         composition.setDateUpdate(new Timestamp(new java.util.Date().getTime()));
         paragraph.setComposition(optional.get());
         return paragraphService.save(paragraph);
      } else
         throw new CompositionNotFoundException();
   }
}