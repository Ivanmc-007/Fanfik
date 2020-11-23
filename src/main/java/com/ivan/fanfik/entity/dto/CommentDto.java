package com.ivan.fanfik.entity.dto;

public class CommentDto {

   private Long userId;

   private String text;

   public String getText() {
      return text;
   }

   public Long getUserId() {
      return userId;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

}