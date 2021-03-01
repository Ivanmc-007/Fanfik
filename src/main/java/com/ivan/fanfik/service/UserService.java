package com.ivan.fanfik.service;

import com.ivan.fanfik.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByName(String name);

    Optional<User> findById(Long id);

}

