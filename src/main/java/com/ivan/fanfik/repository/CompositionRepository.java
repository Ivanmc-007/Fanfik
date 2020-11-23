package com.ivan.fanfik.repository;

import com.ivan.fanfik.entity.Composition;

import com.ivan.fanfik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompositionRepository extends JpaRepository<Composition, Long> {
   List<Composition> findByUser(User user);

   @EntityGraph(attributePaths = { "genres", "tags", "user" })
   Optional<Composition> findById(Long id);

   @Query("SELECT c FROM Composition c WHERE c.id = :id")
   Optional<Composition> findByIdDefault(@Param("id") Long id);

   @Query("SELECT c FROM Composition c JOIN FETCH c.tags t WHERE c.name LIKE %:text% OR t.text LIKE %:text%")
   List<Composition> findByNameOrTagText(@Param("text") String text);

   @EntityGraph(attributePaths = { "tags" })
   List<Composition> findTop15ByOrderByDateUpdateDesc();
}