package com.ivan.fanfik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/"/* , produces = MediaType.APPLICATION_JSON_VALUE */)
public class NavigationController {

   @GetMapping
   public String index() {
      return "index";
   }
}