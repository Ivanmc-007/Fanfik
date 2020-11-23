package com.ivan.fanfik.repository;

import java.util.List;
import java.util.Optional;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Paragraph;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {

   List<Paragraph> findByComposition(Composition composition);

}