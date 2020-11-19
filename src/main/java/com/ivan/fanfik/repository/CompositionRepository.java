package com.ivan.fanfik.repository;

import com.ivan.fanfik.entity.Composition;

import com.ivan.fanfik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface CompositionRepository extends JpaRepository<Composition, Long> {
   List<Composition> findByUser(User user);

   @EntityGraph(attributePaths = { "genres", "tags", "user" })
   Optional<Composition> findById(Long id);
}