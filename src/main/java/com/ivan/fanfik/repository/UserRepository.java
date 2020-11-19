package com.ivan.fanfik.repository;

import java.util.Optional;

import com.ivan.fanfik.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByName(String name);

//   @EntityGraph(attributePaths = { "compositions" })
//   @Query("SELECT u FROM User u LEFT JOIN Composition c ON c.user = u where u.id = :id")
   Optional<User> findById(Long id);

}