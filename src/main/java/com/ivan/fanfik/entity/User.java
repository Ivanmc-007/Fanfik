package com.ivan.fanfik.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User extends AbstractEntity {

   private String name;

   private String email;

   private String password;

   @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
   @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
   @Enumerated(value = EnumType.STRING)
   private Set<Role> roles;

   @OneToMany(mappedBy="user")
   private List<Composition> compositions;

   @ManyToMany(mappedBy="users")
   private Set<Mark> marks;

   @ManyToMany(mappedBy = "likes")
   private Set<Paragraph> paragraphs;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   public List<Composition> getCompositions() {
      return compositions;
   }

   public void setCompositions(List<Composition> compositions) {
      this.compositions = compositions;
   }

   public Set<Mark> getMarks() {
      return marks;
   }

   public void setMarks(Set<Mark> marks) {
      this.marks = marks;
   }

   public Set<Paragraph> getParagraphs() {
      return paragraphs;
   }

   public void setParagraphs(Set<Paragraph> paragraphs) {
      this.paragraphs = paragraphs;
   }
}