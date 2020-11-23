package com.ivan.fanfik.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;
import com.ivan.fanfik.entity.Comment;
import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Views;
import com.ivan.fanfik.exception.CompositionNotFoundException;
import com.ivan.fanfik.service.CommentService;
import com.ivan.fanfik.service.CompositionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")

public class CommentRESTController {

   private final CommentService commentService;

   private final CompositionService compositionService;

   public CommentRESTController(CommentService commentService, CompositionService compositionService) {
      this.commentService = commentService;
      this.compositionService = compositionService;
   }

   @JsonView({ Views.Comment.class })
   @GetMapping("/compositions/{id}")
   public List<Comment> getByComposition(@PathVariable Long id) {
      Optional<Composition> optional = compositionService.findByIdFast(id);
      if (optional.isEmpty())
         throw new CompositionNotFoundException();
      return commentService.findByComposition(optional.get());
   }

}