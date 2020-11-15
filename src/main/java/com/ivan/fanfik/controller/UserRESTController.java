package com.ivan.fanfik.controller;

import java.util.Optional;
import java.util.Set;

import com.ivan.fanfik.annotation.CurrentUser;
import com.ivan.fanfik.entity.Role;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.entity.View;
import com.ivan.fanfik.exception.UserNotFoundException;
import com.ivan.fanfik.exception.UserWithNameExistsException;
import com.ivan.fanfik.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {

   private final UserService userService;

   public UserRESTController(UserService userService) {
      this.userService = userService;
   }

   @GetMapping("/current-user")
   public User getCurrentUser(@CurrentUser User user) {
      return user;
   }

   @PostMapping
   public User saveUser(@RequestBody User user) {
      Optional<User> optional = userService.findByName(user.getName());
      if (optional.isEmpty()) {
         user.setRoles(Set.of(Role.USER));
         // TODO: remove after -> заглушка подтверждения регистрации
         user.setActive(true);
         return userService.save(user);
      }
      throw new UserWithNameExistsException();
   }

   @JsonView({ View.UserProfile.class })
   @GetMapping("/{id}")
   public User getUser(@PathVariable Long id) {
      return userService.findById(id).orElseThrow(UserNotFoundException::new);
   }
}