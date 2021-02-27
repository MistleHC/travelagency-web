package com.gmail.mistle.ibo.travelagency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.mistle.ibo.travelagency.dao.RoleDAO;
import com.gmail.mistle.ibo.travelagency.model.Role;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;
import com.gmail.mistle.ibo.travelagency.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    /**
     * @return List of all user roles
     * @see Role
     */
    @Override
    public List<Role> getAll() {
        return StreamSupport.stream(roleDAO.findAll().spliterator(), false)
                                           .collect(Collectors.toList());
    }


    /**
     * Find target role by its name
     * @param name role title
     * @return role object
     * @see Role
     */
    @Override
    public Role getByName(String name) {
        return roleDAO.findByName(name)
                      .orElseThrow(NotFoundException::new);
    }

}
