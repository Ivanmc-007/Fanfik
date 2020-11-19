package com.ivan.fanfik.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

@Entity
public class Tag {

   @JsonView({ Views.Composition.class })
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @JsonView({ Views.Composition.class })
   private String text;

   @ManyToMany(mappedBy = "tags")
   private Set<Composition> compositions;

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public Set<Composition> getCompositions() {
      return compositions;
   }

   public void setCompositions(Set<Composition> compositions) {
      this.compositions = compositions;
   }

   public Long getId() {
      return id;
   }
}