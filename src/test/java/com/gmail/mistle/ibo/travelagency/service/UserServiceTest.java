package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.config.hibernate.HibernateConfig;
import com.gmail.mistle.ibo.travelagency.config.webconfig.WebConfig;
import com.gmail.mistle.ibo.travelagency.dao.UserDAO;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;
import com.gmail.mistle.ibo.travelagency.model.User;
import com.gmail.mistle.ibo.travelagency.web.dto.UserInfoDto;
import com.gmail.mistle.ibo.travelagency.web.dto.UserProfileDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class UserServiceTest {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    private Long correctId;

    @Before
    public void init() {
        User user = User.builder()
                .email("george@gmail.com")
                .name("George")
                .password("123")
                .build();

        userDAO.save(user);
        correctId = user.getId();
    }

    @Test
    public void testGetByCorrectId() {
        User user = userService.getById(correctId);
        assertEquals(correctId, user.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByWrongIdRaisesException() {
        userService.getById(correctId + 13);
    }

    @Test
    public void testGetByEmail() {
        User user = userService.getByEmail("george@gmail.com");
        assertEquals("george@gmail.com", user.getEmail());
        assertEquals("George", user.getName());
        assertEquals(correctId, user.getId());
    }

    @Test
    public void testGetUserInfo() {
        UserInfoDto user = userService.getUserInfo(correctId);
        User userExpected = userService.getById(correctId);

        assertEquals(userExpected.getName(), user.getName());
        assertEquals(userExpected.getEmail(), user.getEmail());
        assertEquals(userExpected.getAboutMe(), user.getAboutMe());
    }

    @Test
    public void testUpdateUserInfo() {
        UserProfileDto userProfileDto = UserProfileDto.builder()
                    .userDescription("desc")
                    .userFullName("name")
                    .build();

        User user = userService.updateUserInfo(userProfileDto, correctId);

        assertEquals(userProfileDto.getUserFullName(), user.getFullName());
        assertEquals(userProfileDto.getUserDescription(), user.getAboutMe());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByNotRegisteredEmailRaisesException() {
        userService.getByEmail("someWrongEmail@gmail.com");
    }
}
