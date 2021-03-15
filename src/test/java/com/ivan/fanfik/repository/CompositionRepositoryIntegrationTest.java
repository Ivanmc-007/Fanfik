package com.ivan.fanfik.repository;

import com.ivan.fanfik.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // By default, tests annotated with @DataJpaTest are transactional and roll back at the end of each test
public class CompositionRepositoryIntegrationTest {

    private final static String NAME_OF_COMPOSITION_1 = "Маша и медведь";
    private final static String NAME_OF_COMPOSITION_2 = "Коромысло";

    private final static String SHORT_DESCRIPTION_OF_COMPOSITION_1 = "ShortDescription";
    private final static String SHORT_DESCRIPTION_OF_COMPOSITION_2 = "Приключения коромысла";

    private final static String NAME_OF_GENRE_1 = "Детектив-фантастика";
    private final static String NAME_OF_GENRE_2 = "Детектив-комедия";

    private final static String NAME_OF_TAG_1 = "Маша и случай в лесу";
    private final static String NAME_OF_TAG_2 = "Маша против медведя";
    private final static String NAME_OF_TAG_3 = "Медведь и коромысло";

    private final static String STRING_FOR_SEARCH_1 = "Маша и случай";
    private final static String STRING_FOR_SEARCH_2 = "и медведь";

    private final static String EMAIL_EXAMPLE_1 = "some_email@mail.com";
    private final static String EMAIL_EXAMPLE_2 = "some_email2@mail.com";

    private final static String USER_NAME_1 = "userName";    // as a login
    private final static String USER_NAME_2 = "userName2";    // as a login

    private final static String USER_PASSWORD_1 = "1234";
    private final static String USER_PASSWORD_2 = "1234";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompositionRepository compositionRepository;

    @Autowired
    private GenreRepository genreRepository;

    private static User user1;
    private static User user2;

    private static Genre genre1;
    private static Genre genre2;

    private static Tag tag1;
    private static Tag tag2;
    private static Tag tag3;

    static {
        user1 = new User();
        user1.setActive(true);
        user1.setRoles(Set.of(Role.USER));
        user1.setEmail(EMAIL_EXAMPLE_1);
        user1.setName(USER_NAME_1);
        user1.setPassword(USER_PASSWORD_1);

        user2 = new User();
        user2.setActive(true);
        user2.setRoles(Set.of(Role.USER));
        user2.setEmail(EMAIL_EXAMPLE_2);
        user2.setName(USER_NAME_2);
        user2.setPassword(USER_PASSWORD_2);

        genre1 = new Genre();
        genre1.setName(NAME_OF_GENRE_1);

        genre2 = new Genre();
        genre2.setName(NAME_OF_GENRE_2);

        tag1 = new Tag();
        tag1.setText(NAME_OF_TAG_1);

        tag2 = new Tag();
        tag2.setText(NAME_OF_TAG_2);

        tag3 = new Tag();
        tag3.setText(NAME_OF_TAG_3);
    }

    @Test
    public void whenFindById_thenReturnCompositionWithGenresTagsUser() {
        entityManager.persistAndFlush(user2);

        entityManager.persistAndFlush(genre2);

        entityManager.persistAndFlush(tag3);

        Composition composition = new Composition();
        composition.setUser(user2);
        composition.setGenres(Set.of(genre2));
        composition.setDateUpdate(new Timestamp(new Date().getTime()));
        composition.setName(NAME_OF_COMPOSITION_2);
        composition.setShortDescription(SHORT_DESCRIPTION_OF_COMPOSITION_2);
        composition.setTags(Set.of(tag3));

        entityManager.persist(composition);
        entityManager.flush();

        // when
        Optional<Composition> optional = compositionRepository.findById(composition.getId());
        // then
        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.get().getUser()).isNotNull();
        assertThat(optional.get().getTags()).isNotEmpty();
        assertThat(optional.get().getGenres()).isNotEmpty();
    }

    @Test
    public void whenFindByNameOrTagText_thenReturnCompositionList() {

        entityManager.persistAndFlush(user1);

        entityManager.persistAndFlush(genre1);

        entityManager.persistAndFlush(tag1);

        entityManager.persistAndFlush(tag2);

        Composition composition = new Composition();
        composition.setUser(user1);
        composition.setGenres(Set.of(genre1));
        composition.setDateUpdate(new Timestamp(new Date().getTime()));
        composition.setName(NAME_OF_COMPOSITION_1);
        composition.setShortDescription(SHORT_DESCRIPTION_OF_COMPOSITION_1);
        composition.setTags(Set.of(tag1, tag2));

        entityManager.persist(composition);
        entityManager.flush();

        // when
        List<Composition> compositions = compositionRepository.findByNameOrTagText(STRING_FOR_SEARCH_1);
        // then
        assertThat(compositions.size()).isEqualTo(1L);
        assertThat(compositions.get(0).getName()).isEqualTo(composition.getName());

        // when
        compositions = compositionRepository.findByNameOrTagText(STRING_FOR_SEARCH_2);
        // then
        assertThat(compositions.size()).isEqualTo(1L);
        assertThat(compositions.get(0).getName()).isEqualTo(composition.getName());
    }

    @Test
    @Sql(scripts = { "classpath:dt/migration/test__Insert_Composition.sql" })
    public void whenFindTop15ByOrderByDateUpdateDesc_thenReturnTop15List() {
        List<Composition> compositions = compositionRepository.findTop15ByOrderByDateUpdateDesc();
        assertThat(compositions.size()).isLessThanOrEqualTo(15);
    }

    @Test
    @Sql(scripts = { "classpath:dt/migration/test__Insert_Composition.sql" })
    public void whenFindByIdDefault_thenReturnComposition() {
        // data should be the same as in db migration
        Optional<Composition> optional = compositionRepository.findByIdDefault(10L);
        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.get().getName()).isEqualTo("Composition name first");
    }

}
