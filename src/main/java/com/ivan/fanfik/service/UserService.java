package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.User;

<<<<<<< HEAD
import java.util.Optional;
=======
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
>>>>>>> dev

public interface UserService {

    User save(User user);

<<<<<<< HEAD
    Optional<User> findByName(String name);

    Optional<User> findById(Long id);
}

=======
   @Transactional(rollbackFor = { Exception.class })
   public User save(User user) {
      return userRepository.save(user);
   }

   @Transactional(readOnly = true)
   public Optional<User> findByName(String name) {
      return userRepository.findByName(name);
   }

   @Transactional(readOnly = true)
   public Optional<User> findById(Long id) {
      return userRepository.findById(id);
   }
}
>>>>>>> dev
