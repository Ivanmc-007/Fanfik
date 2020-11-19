package com.ivan.fanfik.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Composition {

   @JsonView({ Views.Composition.class })
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @JsonView({ Views.Composition.class })
   private String name;

   @JsonView({ Views.Composition.class })
   private String shortDescription;

   private Date dateUpdate;

   @JsonView({ Views.Composition.class })
   @ManyToMany
   @JoinTable(name = "composition_genre", joinColumns = @JoinColumn(name = "composition_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
   private Set<Genre> genres;

   @JsonView({ Views.Composition.class })
   @ManyToMany
   @JoinTable(name = "composition_tag", joinColumns = @JoinColumn(name = "composition_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
   private Set<Tag> tags;

   @OneToMany(mappedBy = "composition")
   private List<Paragraph> paragraphs;

   @JsonView({ Views.Composition.class })
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToMany
   @JoinTable(name = "composition_mark", joinColumns = @JoinColumn(name = "composition_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "mark_id", referencedColumnName = "id"))
   private List<Mark> marks;

   @OneToMany(mappedBy = "composition")
   private List<Comment> comments;

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getShortDescription() {
      return shortDescription;
   }

   public void setShortDescription(String shortDescription) {
      this.shortDescription = shortDescription;
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

   public Date getDateUpdate() {
      return dateUpdate;
   }

   public void setDateUpdate(Date dateUpdate) {
      this.dateUpdate = dateUpdate;
   }

   public List<Comment> getComments() {
      return comments;
   }

   public void setComments(List<Comment> comments) {
      this.comments = comments;
   }
}