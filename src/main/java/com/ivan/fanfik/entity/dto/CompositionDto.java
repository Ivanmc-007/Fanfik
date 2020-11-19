package com.ivan.fanfik.entity.dto;

import java.util.List;

public class CompositionDto {
   private String name;

   private String shortDescription;

   private List<Long> genres;

   private List<String> tags;

   public String getName() {
      return name;
   }

   public String getShortDescription() {
      return shortDescription;
   }

   public List<Long> getGenres() {
      return genres;
   }

   public List<String> getTags() {
      return tags;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setShortDescription(String shortDescription) {
      this.shortDescription = shortDescription;
   }

   public void setGenres(List<Long> genres) {
      this.genres = genres;
   }

   public void setTags(List<String> tags) {
      this.tags = tags;
   }
}