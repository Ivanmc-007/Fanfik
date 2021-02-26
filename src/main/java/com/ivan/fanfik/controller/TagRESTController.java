package com.ivan.fanfik.controller;

import java.util.List;

import com.ivan.fanfik.entity.Tag;
import com.ivan.fanfik.service.TagServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagRESTController {

   private final TagServiceImpl tagServiceImpl;

   public TagRESTController(TagServiceImpl tagServiceImpl) {
      this.tagServiceImpl = tagServiceImpl;
   }

   @GetMapping
   public List<Tag> findAll() {
      return tagServiceImpl.findAll();
   }

}