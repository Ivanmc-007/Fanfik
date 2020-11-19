package com.ivan.fanfik.repository;

import java.util.Collection;
import java.util.List;

import com.ivan.fanfik.entity.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
   List<Tag> findByTextIn(Collection<String> texts);
}