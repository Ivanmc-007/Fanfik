package com.ivan.fanfik.repository;

import java.util.List;

import com.ivan.fanfik.entity.Comment;
import com.ivan.fanfik.entity.Composition;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

   List<Comment> findByComposition(Composition composition);
   
}