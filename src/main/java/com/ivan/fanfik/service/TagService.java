package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Tag;
<<<<<<< HEAD

import java.util.List;
import java.util.Set;

public interface TagService {
=======
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
>>>>>>> dev

    List<Tag> findAll();

    Set<Tag> saveByText(List<String> texts);

}
