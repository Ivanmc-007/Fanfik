package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Tag;
import java.util.List;
import java.util.Set;

public interface TagService {

   List<Tag> findAll();

   Set<Tag> saveByText(List<String> texts);

}
