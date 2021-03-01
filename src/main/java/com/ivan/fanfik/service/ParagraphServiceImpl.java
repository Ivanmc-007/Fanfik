package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Composition;
import com.ivan.fanfik.entity.Paragraph;
import com.ivan.fanfik.repository.ParagraphRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParagraphServiceImpl implements ParagraphService {

    private final ParagraphRepository paragraphRepository;

    public ParagraphServiceImpl(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    @Transactional(readOnly = true)
    public List<Paragraph> findByComposition(Composition composition) {
        return paragraphRepository.findByComposition(composition);
    }

    @Transactional(rollbackFor = { Exception.class })
    public Paragraph save(Paragraph paragraph) {
        return paragraphRepository.save(paragraph);
    }

}
