package com.ivan.fanfik.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Comment extends AbstractEntity {

   @JsonView({ Views.Comment.class })
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @JsonView({ Views.Comment.class })
   private String text;

   @JsonView({ Views.Comment.class })
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne
   @JoinColumn(name = "composition_id")
   private Composition composition;

   public Long getId() {
      return id;
   }

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