package com.ivan.fanfik.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @JsonView({ View.UserProfile.class })
   private Long id;

   @JsonView({ View.UserProfile.class })
   private String name;

   @JsonView({ View.UserProfile.class })
   private String email;

   private String password;

   @JsonView({ View.UserProfile.class })
   private boolean active;

   @JsonView({ View.UserProfile.class })
   @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
   @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
   @Enumerated(value = EnumType.STRING)
   private Set<Role> roles;

   @JsonView({ View.UserProfile.class })
   @OneToMany(mappedBy = "user")
   private List<Composition> compositions = new ArrayList<>();

   @ManyToMany(mappedBy = "users")
   private Set<Mark> marks;

   @ManyToMany(mappedBy = "likes")
   private Set<Paragraph> paragraphs; // каким главам пользователь поставил like

   public Long getId() {
      return id;
   }

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

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }

   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return name;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return active;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isActive() {
      return active;
   }

   public void setActive(boolean active) {
      this.active = active;
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