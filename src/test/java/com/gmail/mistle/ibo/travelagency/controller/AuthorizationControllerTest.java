package com.gmail.mistle.ibo.travelagency.controller;

import com.gmail.mistle.ibo.travelagency.config.hibernate.HibernateConfig;
import com.gmail.mistle.ibo.travelagency.config.webconfig.WebConfig;
import com.gmail.mistle.ibo.travelagency.web.controller.AuthorizationController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class AuthorizationControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private AuthorizationController authorizationController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(authorizationController).build();
    }

    @Test
    public void testRegisterViewName() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }
}
