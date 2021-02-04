package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.web.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.mistle.ibo.travelagency.dao.UserDAO;
import com.gmail.mistle.ibo.travelagency.model.User;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;
import com.gmail.mistle.ibo.travelagency.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> getAllByRole(String role) {
        return userDAO.findAllByRole(role);
    }

    @Override
    public User getById(Long id) {
        return userDAO.findUserById(id)
                      .orElseThrow(NotFoundException::new);
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.findUserByEmail(email)
                      .orElseThrow(NotFoundException::new);
    }

    @Override
    public UserInfoDto getUserInfo(Long id) {
        User user = getById(id);

        return UserInfoDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .aboutMe(user.getAboutMe())
                .fullName(user.getFullName())
                .orders(user.getOrders())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

}
