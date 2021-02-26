package com.ivan.fanfik.service;

import java.util.List;

import com.ivan.fanfik.entity.Comment;
import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.repository.CommentRepository;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

   private final CommentRepository commentRepository;

   public CommentService(CommentRepository commentRepository) {
      this.commentRepository = commentRepository;
   }

   public Comment save(Comment comment) {
      return commentRepository.save(comment);
   }

   public List<Comment> findByComposition(Composition composition) {
      return commentRepository.findByComposition(composition);
   }
}