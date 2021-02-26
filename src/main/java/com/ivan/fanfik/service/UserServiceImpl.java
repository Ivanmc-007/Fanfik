package com.ivan.fanfik.service;

import java.util.Optional;

import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public User save(User user) {
      return userRepository.save(user);
   }

   public Optional<User> findByName(String name) {
      return userRepository.findByName(name);
   }

   public Optional<User> findById(Long id) {
      return userRepository.findById(id);
   }

}