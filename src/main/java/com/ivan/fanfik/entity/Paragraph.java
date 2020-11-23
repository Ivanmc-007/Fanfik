package com.ivan.fanfik.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

@Entity
public class Paragraph {

   @JsonView({ Views.Paragraph.class })
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @JsonView({ Views.Paragraph.class })
   private String name; // title

   @JsonView({ Views.Paragraph.class })
   private String text;

   @JsonView({ Views.Paragraph.class })
   private String linkToImage;

   @JsonView({ Views.Paragraph.class })
   @ManyToOne
   @JoinColumn(name = "composition_id")
   private Composition composition;

   @ManyToMany
   @JoinTable(name = "paragraph_user", joinColumns = @JoinColumn(name = "paragraph_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
   private Set<User> likes; // какие пользователи поставили like

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public String getLinkToImage() {
      return linkToImage;
   }

   public void setLinkToImage(String linkToImage) {
      this.linkToImage = linkToImage;
   }

   public Composition getComposition() {
      return composition;
   }

   public void setComposition(Composition composition) {
      this.composition = composition;
   }

   public Set<User> getLikes() {
      return likes;
   }

   public void setLikes(Set<User> likes) {
      this.likes = likes;
   }
}