package com.ivan.fanfik.repository;

import com.ivan.fanfik.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TagRepositoryIntegrationTest {

    private final static String TEXT_OF_TAG_1 = "one";
    private final static String TEXT_OF_TAG_2 = "Some new text for tag";
    private final static String TEXT_OF_TAG_3 = "three";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void whenFindByTextIn_thenReturnTagList() {
        Tag tag = new Tag();
        tag.setText(TEXT_OF_TAG_2);

        entityManager.persist(tag);
        entityManager.flush();

        // when
        List<Tag> tags = tagRepository.findByTextIn(List.of(TEXT_OF_TAG_1, TEXT_OF_TAG_3));
        // then
        assertThat(tags).isEmpty();
        // when
        tags = tagRepository.findByTextIn(List.of(TEXT_OF_TAG_1, TEXT_OF_TAG_2, TEXT_OF_TAG_3));
        // then
        assertThat(tags).isNotEmpty();
    }

}
