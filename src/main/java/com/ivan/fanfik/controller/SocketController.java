package com.ivan.fanfik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ivan.fanfik.entity.Comment;
import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.entity.Views;
import com.ivan.fanfik.entity.dto.CommentDto;
import com.ivan.fanfik.exception.CompositionNotFoundException;
import com.ivan.fanfik.exception.UserNotFoundException;
import com.ivan.fanfik.service.CommentService;
import com.ivan.fanfik.service.CompositionService;
import com.ivan.fanfik.service.UserService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SocketController {

   private final CompositionService compositionService;

   private final UserService userService;

   private final CommentService commentService;

   public SocketController(CompositionService compositionService, UserService userService,
         CommentService commentService) {
      this.compositionService = compositionService;
      this.userService = userService;
      this.commentService = commentService;
   }

   @JsonView({ Views.Comment.class })
   @MessageMapping("/comment-add/{compositionId}")
   @SendTo("/topic/comments/{compositionId}")
   public Comment send(@DestinationVariable Long compositionId, CommentDto commentDto) {
      // TODO: пускай возвращает Response + status + newComment
      // TODO: перенести логику в service
      Optional<User> optionalUser = userService.findById(commentDto.getUserId());
      if (optionalUser.isEmpty())
         throw new UserNotFoundException();
      Optional<Composition> optionalComposition = compositionService.findByIdFast(compositionId);
      if (optionalComposition.isEmpty())
         throw new CompositionNotFoundException();
      Comment comment = new Comment();
      comment.setUser(optionalUser.get());
      comment.setComposition(optionalComposition.get());
      comment.setText(commentDto.getText());
      return commentService.save(comment);
   }

}