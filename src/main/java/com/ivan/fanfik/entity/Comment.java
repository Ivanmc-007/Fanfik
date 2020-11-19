package com.ivan.fanfik.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends AbstractEntity {

   private String text;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne
   @JoinColumn(name = "composition_id")
   private Composition composition;

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public Composition getComposition() {
      return composition;
   }

   public void setComposition(Composition composition) {
      this.composition = composition;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

}