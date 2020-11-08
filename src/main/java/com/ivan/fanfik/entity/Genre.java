package com.ivan.fanfik.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre extends AbstractEntity {

   private String name;

   @ManyToMany(mappedBy = "genres")
   private Set<Composition> compositions;

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