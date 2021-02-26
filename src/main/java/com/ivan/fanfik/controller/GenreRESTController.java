package com.ivan.fanfik.controller;

import com.ivan.fanfik.entity.Genre;
import com.ivan.fanfik.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreRESTController {

   private final GenreService genreService;

   public GenreRESTController(GenreService genreService) {
      this.genreService = genreService;
   }

   @GetMapping
   public List<Genre> getAll() {
      return genreService.findAll();
   }

}