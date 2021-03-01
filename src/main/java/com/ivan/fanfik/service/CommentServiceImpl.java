package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Comment;
import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(rollbackFor = { Exception.class })
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> findByComposition(Composition composition) {
        return commentRepository.findByComposition(composition);
    }

}
