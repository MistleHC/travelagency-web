package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Long> {
    List<User> findAll();

    @Query("select u from User u where exists (select r from u.roles r where r.name=:role)")
    List<User> findAllByRole(@Param("role") String role);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
}
