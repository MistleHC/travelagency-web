package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();
    List<User> findAllByRole(String role);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    void save(User user);
}
