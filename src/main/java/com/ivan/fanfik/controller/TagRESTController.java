package com.ivan.fanfik.controller;

import java.util.List;

import com.ivan.fanfik.entity.Tag;
import com.ivan.fanfik.service.TagService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagRESTController {

   private final TagService tagService;

   public TagRESTController(TagService tagService) {
      this.tagService = tagService;
   }

   @GetMapping
   public List<Tag> findAll() {
      return tagService.findAll();
   }

}