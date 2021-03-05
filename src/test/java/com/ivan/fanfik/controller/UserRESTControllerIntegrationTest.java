package com.ivan.fanfik.controller;

import com.ivan.fanfik.Application;
import com.ivan.fanfik.entity.Role;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@Transactional // будет обнулять данные после выполнения каждого тестового метода
public class UserRESTControllerIntegrationTest {

    // данные должны совпадать с тестовой миграцией
    private final static long DB_USER_ID = 10L;
    private final static String DB_USER_NAME = "userNameTest";
    private final static String DB_USER_EMAIL = "user-email-test@mail.com";
    private final static boolean DB_USER_ACTIVE = true;
    private final static Role DB_USER_ROLE = Role.USER;

    private final static String STRING_EXPRESSION =
            String.format(
            "$[?(@.id == %d && @.name == '%s' && @.email == '%s' && @.active == %s && '%s' in @.roles)]",
                    DB_USER_ID, DB_USER_NAME, DB_USER_EMAIL, DB_USER_ACTIVE, DB_USER_ROLE.name());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loadContext() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void whenValidInput_thenCreateUser() throws Exception {
        User user = new User();
        user.setName("newUserName");
        user.setEmail("new-user-email@mail.com");
        user.setPassword("1");

        List<User> listBefore = userRepository.findAll();

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)))
                .andExpect(status().isOk());

        // to do that again
        // shouldn't persist because user with this name already exists
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)));

        List<User> listAfter = userRepository.findAll();
        assertThat(listAfter.size()).isEqualTo(listBefore.size() + 1);
    }

    @Test
    @Sql(scripts = { "classpath:dt/migration/test__Insert_User.sql" })
    public void whenGetUserById() throws Exception {
        mockMvc.perform(get("/api/users/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(STRING_EXPRESSION)
                        .exists());
    }


}
