package com.ivan.fanfik.repository;

import java.util.Optional;

import com.ivan.fanfik.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByName(String name);

   @EntityGraph(attributePaths = { "compositions" })
   Optional<User> findById(Long id);

}