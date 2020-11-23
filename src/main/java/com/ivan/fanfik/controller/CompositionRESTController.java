package com.ivan.fanfik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ivan.fanfik.annotation.CurrentUser;
import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Tag;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.entity.Views;
import com.ivan.fanfik.entity.dto.CompositionDto;
import com.ivan.fanfik.exception.CompositionNotFoundException;
import com.ivan.fanfik.exception.UnauthorizedUserException;
import com.ivan.fanfik.service.CompositionService;
import com.ivan.fanfik.service.GenreService;
import com.ivan.fanfik.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/compositions")
public class CompositionRESTController {

   private final GenreService genreService;

   private final TagService tagService;

   private final CompositionService compositionService;

   public CompositionRESTController(GenreService genreService, TagService tagService,
         CompositionService compositionService) {
      this.genreService = genreService;
      this.tagService = tagService;
      this.compositionService = compositionService;
   }

   @JsonView({ Views.Composition.class })
   @GetMapping("/{id}")
   public Composition getCompositionById(@PathVariable Long id) {
      return compositionService.findById(id).orElseThrow(CompositionNotFoundException::new);
   }

   @PostMapping
   public Composition createNew(@RequestBody CompositionDto compositionDto, @CurrentUser User user) {
      // TODO: найти библиотеку-конвертер DTO -> Entity
      // TODO: и перенести в service
      if (Objects.nonNull(user)) {
         Composition composition = new Composition();
         composition.setUser(user);
         composition.setName(compositionDto.getName());
         composition.setShortDescription(compositionDto.getShortDescription());
         composition.setGenres(genreService.findAllById(compositionDto.getGenres()));
         Set<Tag> tags = tagService.saveByText(compositionDto.getTags());
         composition.setTags(tags);
         return compositionService.save(composition);
      }
      throw new UnauthorizedUserException();
   }

   @GetMapping("/current-user")
   public List<Composition> getByUserId(@CurrentUser User user) {
      return compositionService.findByUser(user);
   }

   @JsonView({ Views.CompositionSearch.class })
   @GetMapping("/search")
   public List<Composition> getByAll(@RequestParam(name = "text", defaultValue = "") String searchText) {
      return compositionService.findBySearchText(searchText);
   }

   @JsonView({ Views.CompositionSearch.class })
   @GetMapping("/newest")
   public List<Composition> newest() {
      return compositionService.findByNewestDateUpdate();
   }

}