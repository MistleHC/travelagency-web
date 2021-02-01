package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getByName(String name);
}
