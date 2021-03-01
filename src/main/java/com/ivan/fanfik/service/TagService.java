package com.ivan.fanfik.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ivan.fanfik.entity.Tag;
import com.ivan.fanfik.repository.TagRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {

   private final TagRepository tagRepository;

   public TagService(TagRepository tagRepository) {
      this.tagRepository = tagRepository;
   }

   @Transactional(readOnly = true)
   public List<Tag> findAll() {
      return tagRepository.findAll();
   }

   @Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
   public Set<Tag> saveByText(List<String> texts) {
      // выбрать те что уже есть в БД
      List<Tag> tags = tagRepository.findByTextIn(texts);
      Set<String> newStrTexts = new HashSet<>();
      for (String str : texts) {
         if (!textExists(str, tags))
            newStrTexts.add(str);
      }
      // выбрать те, корых ещё нет в БД
      List<Tag> newTags = newStrTexts.stream().map(text -> {
         Tag tag = new Tag();
         tag.setText(text);
         return tag;
      }).collect(Collectors.toList());

      newTags = tagRepository.saveAll(newTags);
      tags.addAll(newTags);
      return new HashSet<>(tags);
   }

   private boolean textExists(String text, List<Tag> tags) {
      for (Tag tag : tags) {
         if (text.equals(tag.getText()))
            return true;
      }
      return false;
   }

}