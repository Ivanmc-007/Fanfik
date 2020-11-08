package com.ivan.fanfik.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Tag extends AbstractEntity {

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
}