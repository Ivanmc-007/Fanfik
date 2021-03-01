package com.ivan.fanfik.service;

import java.util.Optional;

import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Transactional(rollbackFor = { Exception.class })
   @Override
   public User save(User user) {
      return userRepository.save(user);
   }

   @Transactional(readOnly = true)
   @Override
   public Optional<User> findByName(String name) {
      return userRepository.findByName(name);
   }

   @Transactional(readOnly = true)
   @Override
   public Optional<User> findById(Long id) {
      return userRepository.findById(id);
   }

}