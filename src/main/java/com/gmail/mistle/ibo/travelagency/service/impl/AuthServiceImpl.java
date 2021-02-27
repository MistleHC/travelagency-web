package com.gmail.mistle.ibo.travelagency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.mistle.ibo.travelagency.config.constants.UserRoles;
import com.gmail.mistle.ibo.travelagency.model.Role;
import com.gmail.mistle.ibo.travelagency.model.User;
import com.gmail.mistle.ibo.travelagency.service.AuthService;
import com.gmail.mistle.ibo.travelagency.service.RoleService;
import com.gmail.mistle.ibo.travelagency.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    /**
     * @param userToRegister user object that should be registered
     * @see User
     * @see Role 'CUSTOMER' role should be asigned for new user
     */
    @Override
    public void registerUser(User userToRegister) {
        encodeUsersPassword(userToRegister);
        userToRegister.setRoles(new HashSet<>());
        attachRoleToUser(UserRoles.CUSTOMER, userToRegister);
        userService.save(userToRegister);
    }

    /**
     * @param user encode user password
     */
    private void encodeUsersPassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    /**
     * Grant privileges for specific user
     * @param role granted role
     * @param user target user
     * @see Role
     */
    private void attachRoleToUser(UserRoles role, User user) {
        Set<Role> roles = user.getRoles();
        Role desiredRole = roleService.getByName(role.toString());
        roles.add(desiredRole);
        user.setRoles(roles);
    }
}
