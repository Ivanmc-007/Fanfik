package com.ivan.fanfik.controller;

import com.ivan.fanfik.entity.Genre;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.service.GenreService;
import com.ivan.fanfik.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreRESTController.class)
public class GenreRESTControllerIntegrationTest {

    private final static String STRING_EXPRESSION = "$[?(@.id == %d && @.name == '%s')]";

    private final static String GENRE_NAME_1 = "Детектив";
    private final static String GENRE_NAME_2 = "Комедия";
    private final static String GENRE_NAME_3 = "Фантастика";

    private final static Long GENRE_ID_1 = 1001L;
    private final static Long GENRE_ID_2 = 1002L;
    private final static Long GENRE_ID_3 = 1003L;

    private static List<Genre> genres;

    static {
        genres = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName(GENRE_NAME_1);
        ReflectionTestUtils.setField(genre, "id", GENRE_ID_1);
        genres.add(genre);

        genre = new Genre();
        genre.setName(GENRE_NAME_2);
        ReflectionTestUtils.setField(genre, "id", GENRE_ID_2);
        genres.add(genre);

        genre = new Genre();
        genre.setName(GENRE_NAME_3);
        ReflectionTestUtils.setField(genre, "id", GENRE_ID_3);
        genres.add(genre);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @MockBean
    private UserService userService;

    @Before
    public void metBefore() {
        // подменяем userService, метод которого вызывается в WebSecurityConfig.class
        // при использовании @WebMvcTest помогает не упасть, т.к. она сканирует только Controllers
        // и userService для нас не 'существует' на момент выполнения теста
        given(userService.findByName(any(String.class))).willReturn(Optional.of(new User()));
    }

    @Test
    public void givenGenres_whenGetGenres_thenReturnJsonArray() throws Exception {
        given(genreService.findAll()).willReturn(genres);

        mockMvc.perform(get("/api/genres")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath(String.format(STRING_EXPRESSION, GENRE_ID_2, GENRE_NAME_2)).exists())
                .andExpect(jsonPath(String.format(STRING_EXPRESSION, GENRE_ID_1, GENRE_NAME_1)).exists())
                .andExpect(jsonPath(String.format(STRING_EXPRESSION, GENRE_ID_3, GENRE_NAME_3)).exists());
    }


}
