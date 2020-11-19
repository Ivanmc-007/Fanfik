package com.ivan.fanfik.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

@Entity
public class Genre {

   @JsonView({ Views.Composition.class })
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @JsonView({ Views.Composition.class })
   private String name;

   @ManyToMany(mappedBy = "genres")
   private Set<Composition> compositions;

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Set<Composition> getCompositions() {
      return compositions;
   }

   public void setCompositions(Set<Composition> compositions) {
      this.compositions = compositions;
   }
}