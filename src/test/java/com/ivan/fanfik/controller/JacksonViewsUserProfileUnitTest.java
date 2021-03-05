package com.ivan.fanfik.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.fanfik.entity.Role;
import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.entity.Views;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonViewsUserProfileUnitTest {

    private final static Long USER_ID = 21L;
    private final static String USER_NAME = "userName";
    private final static String USER_EMAIL = "user-email@mail.com";
    private final static boolean USER_ACTIVE = true;
    private final static String USER_PASSWORD = "1";
    private final static Role USER_ROLE = Role.USER;

    private User user;

    @Before
    public void metBefore() {
        user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setActive(USER_ACTIVE);
        user.setPassword(USER_PASSWORD);
        user.setRoles(Set.of(USER_ROLE));
        ReflectionTestUtils.setField(user, "id", USER_ID);
    }

    @Test
    public void whenSerializeToJson_thenCorrect() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        String result = mapper
                .writerWithView(Views.UserProfile.class)
                .writeValueAsString(user);

        assertThat(result).contains(String.valueOf(USER_ID));
        assertThat(result).contains(USER_NAME);
        assertThat(result).contains(USER_EMAIL);
        assertThat(result).contains(String.valueOf(USER_ACTIVE));
        assertThat(result).contains(USER_ROLE.name());
        assertThat(result).doesNotContain("password");
    }

    @Test
    public void whenDeserializeFromJson_thenCorrect() throws JsonProcessingException {
        String json = String.format(
                "{\"id\":%d,\"name\":\"%s\",\"email\":\"%s\",\"active\":%s,\"roles\":[\"%s\"]}",
                USER_ID, USER_NAME, USER_EMAIL, USER_ACTIVE, USER_ROLE.name());

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper
                .readerWithView(Views.UserProfile.class)
                .forType(User.class)
                .readValue(json);

        assertThat(user.getId()).isEqualTo(USER_ID);
        assertThat(user.getName()).isEqualTo(USER_NAME);
        assertThat(user.getEmail()).isEqualTo(USER_EMAIL);
        assertThat(user.getRoles()).contains(USER_ROLE);
        assertThat(user.isActive()).isEqualTo(USER_ACTIVE);
    }

}
