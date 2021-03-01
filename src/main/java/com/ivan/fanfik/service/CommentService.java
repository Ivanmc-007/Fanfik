package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Comment;
import com.ivan.fanfik.entity.Composition;

import java.util.List;

public interface CommentService {

   Comment save(Comment comment);

   List<Comment> findByComposition(Composition composition);

}