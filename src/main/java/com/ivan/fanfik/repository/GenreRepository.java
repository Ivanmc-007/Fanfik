package com.ivan.fanfik.repository;

import com.ivan.fanfik.entity.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}