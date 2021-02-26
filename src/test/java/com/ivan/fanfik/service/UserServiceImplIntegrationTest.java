package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.User;
import com.ivan.fanfik.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplIntegrationTest {

    private final static String USER_NAME_1 = "Bob";
    private final static String USER_NAME_2 = "Alvarez";
    private final static Long USER_ID_1 = 1L;
    private final static Long USER_ID_2 = 2L;

    private final static User userTest;

    static {
        userTest = new User();
        ReflectionTestUtils.setField(userTest, "id", USER_ID_1);
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void metBefore() {
        Mockito.when(userRepository.findByName(any(String.class))).thenAnswer((Answer<Optional<User>>) invocation -> {
            String name = (String) invocation.getArguments()[0];
            userTest.setName(name);
            return Optional.of(userTest);
        });

        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0, User.class);
            ReflectionTestUtils.setField(user,"id", USER_ID_2);
            return user;
        });
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        assertThat(userService.findByName(USER_NAME_1).isPresent()).isTrue();
        assertThat(userService.findByName(USER_NAME_1).get().getId()).isEqualTo(USER_ID_1);
    }

    @Test
    public void whenSave_thenReturnNewUser() {
        User user = new User();
        user.setName(USER_NAME_2);
        User newUser = userService.save(user);
        assertThat(newUser.getName()).isEqualTo(USER_NAME_2);
        assertThat(newUser.getId()).isEqualTo(USER_ID_2);
    }

}
