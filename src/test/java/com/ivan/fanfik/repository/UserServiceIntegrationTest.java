package com.ivan.fanfik.repository;

import com.ivan.fanfik.entity.Role;
import com.ivan.fanfik.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceIntegrationTest {

    private final static String USER_NAME_1 = "John";
    private final static String USER_NAME_2 = "Bob";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void whenFindByName_thenReturnUser() {
        createUserByName(USER_NAME_1);
        createUserByName(USER_NAME_2);
        assertThat(userRepository.findByName(USER_NAME_1).isPresent()).isTrue();
        assertThat(userRepository.findByName(USER_NAME_2).isPresent()).isTrue();
    }

    private void createUserByName(String name) {
        User user = new User();
        user.setName(name);
        user.setPassword("1234");
        user.setEmail("");
        user.setRoles(Set.of(Role.USER));
        user.setActive(true);
        user.setCompositions(List.of());
        testEntityManager.persistAndFlush(user);
    }

}
