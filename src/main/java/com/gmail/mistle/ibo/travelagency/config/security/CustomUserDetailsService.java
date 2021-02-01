package com.gmail.mistle.ibo.travelagency.config.security;

import com.gmail.mistle.ibo.travelagency.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.mistle.ibo.travelagency.dao.UserDAO;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDAO userDAO;
    private final com.gmail.mistle.ibo.travelagency.config.security.UserDetailsFactory userDetailsFactory;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> foundUser = userDAO.findUserByEmail(email);
        if (!foundUser.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        User userToLoad = foundUser.get();
        log.info("Loaded user by email {}", email);
        return userDetailsFactory.create(userToLoad);
    }

}
