package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {
    List<Role> findAll();
    Optional<Role> findByName(String name);
    void save(Role role);
}
