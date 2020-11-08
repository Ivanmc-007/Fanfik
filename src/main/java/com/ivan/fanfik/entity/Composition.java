package com.ivan.fanfik.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Composition extends AbstractEntity {

   private String name;

   private String shotDescription;

   @ManyToMany
   @JoinTable(name = "composition_genre", joinColumns = @JoinColumn(name = "composition_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
   private Set<Genre> genres;

   @ManyToMany
   @JoinTable(name = "composition_tag", joinColumns = @JoinColumn(name = "composition_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
   private Set<Tag> tags;

   @OneToMany(mappedBy = "composition")
   private List<Paragraph> paragraphs;

   @ManyToOne
   @JoinColumn(name="user_id")
   private User user;

   @ManyToMany
   @JoinTable(name="composition_mark",
           joinColumns=
           @JoinColumn(name="composition_id", referencedColumnName="id"),
           inverseJoinColumns=
           @JoinColumn(name="mark_id", referencedColumnName="id"))
   private List<Mark> marks;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getShotDescription() {
      return shotDescription;
   }

   public void setShotDescription(String shotDescription) {
      this.shotDescription = shotDescription;
   }

   public Set<Genre> getGenres() {
      return genres;
   }

   public void setGenres(Set<Genre> genres) {
      this.genres = genres;
   }

   public Set<Tag> getTags() {
      return tags;
   }

   public void setTags(Set<Tag> tags) {
      this.tags = tags;
   }

   public List<Paragraph> getParagraphs() {
      return paragraphs;
   }

   public void setParagraphs(List<Paragraph> paragraphs) {
      this.paragraphs = paragraphs;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public List<Mark> getMarks() {
      return marks;
   }

   public void setMarks(List<Mark> marks) {
      this.marks = marks;
   }
}