package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.Tag;
import com.ivan.fanfik.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class TagServiceImplIntegrationTest {

    private static List<String> strTags = List.of("one", "two", "three", "exists");
    private static List<String> strTagsExistInDB = List.of("two", "exists");
    private static Long COUNT_DB_ID = 1000L;

    private static List<Tag> tagsExistInDB = strTagsExistInDB.stream().map(str -> {
            Tag tag = new Tag();
            tag.setText(str);
            // set some value for private field
            ReflectionTestUtils.setField(tag, "id", COUNT_DB_ID++);
            return tag;
        }).collect(Collectors.toList());

    @TestConfiguration
    static class TagServiceTestContextConfiguration {

        @Autowired
        private TagRepository tagRepository;

        @Bean
        public TagService tagService() {
            return new TagServiceImpl(tagRepository);
        }

    }

    @Autowired
    private TagService tagService;

    @MockBean
    private TagRepository tagRepository;

    @Before
    public void metBefore() {
        // подменяем метод "findByTextIn(...)"
        Mockito.when(tagRepository.findByTextIn(strTags)).thenReturn(tagsExistInDB);

        // подменяем метод "saveAll(...)"
        Mockito.when(tagRepository.saveAll(any(List.class))).thenAnswer((Answer<List<Tag>>) invocation -> {
            List<Tag> tags = (List)invocation.getArguments()[0];

            return tags.stream().peek(tag -> {
                ReflectionTestUtils.setField(tag, "id", COUNT_DB_ID++);
            }).collect(Collectors.toList());
        });

    }

    @Test
    public void whenSaveByText_thenReturnListOfSavedTags() {
        // when
        Set<Tag> tags = tagService.saveByText(strTags);
        // then
        assertThat(tags.size()).isEqualTo(strTags.size());
        tags.forEach(tag -> assertThat(tag.getId()).isNotNull());
    }
}
