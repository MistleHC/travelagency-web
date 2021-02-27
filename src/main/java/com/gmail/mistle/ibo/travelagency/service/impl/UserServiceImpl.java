package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.web.dto.UserInfoDto;
import com.gmail.mistle.ibo.travelagency.web.dto.UserProfileDto;
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

    /**
     * @return list of all users
     */
    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    /**
     * @param id target users ID
     * @return target user object
     */
    @Override
    public User getById(Long id) {
        return userDAO.findUserById(id)
                      .orElseThrow(NotFoundException::new);
    }

    /**
     * @param email target users email
     * @return target user object
     */
    @Override
    public User getByEmail(String email) {
        return userDAO.findUserByEmail(email)
                      .orElseThrow(NotFoundException::new);
    }

    /**
     * @param id users ID
     * @see UserInfoDto
     * @return Users information object that do not contain users password
     */
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

    /**
     * @param user Save user object
     */
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    /**
     * Update user details information
     * @param userProfileDto user details information
     * @param userId targed users ID
     * @return updated user object
     */
    @Override
    @Transactional
    public User updateUserInfo(UserProfileDto userProfileDto, Long userId) {
        User user = getById(userId);
        user.setFullName(userProfileDto.getUserFullName());
        user.setAboutMe(userProfileDto.getUserDescription());
        return user;
    }

}
