package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Paragraph;

import java.util.List;

public interface ParagraphService {

   List<Paragraph> findByComposition(Composition composition);

   Paragraph save(Paragraph paragraph);

}