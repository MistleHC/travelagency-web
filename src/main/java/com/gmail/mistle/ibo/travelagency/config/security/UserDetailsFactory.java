package com.gmail.mistle.ibo.travelagency.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import com.gmail.mistle.ibo.travelagency.model.User;

public interface UserDetailsFactory {
    UserDetails create(User user);
}
