package com.gmail.mistle.ibo.travelagency.config.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface SecurityService {
    Optional<UserDetails> findLoggedInUserDetails();
    void autoLogin(String email, String password);
}
