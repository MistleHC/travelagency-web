package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.config.constants.UserRoles;
import com.gmail.mistle.ibo.travelagency.model.User;

public interface AuthService {
    void registerUser(User userToRegister);
    void toggleRoleOfUser(UserRoles role, User user);
}
