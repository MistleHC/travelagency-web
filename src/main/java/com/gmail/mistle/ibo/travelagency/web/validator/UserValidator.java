package com.gmail.mistle.ibo.travelagency.web.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.gmail.mistle.ibo.travelagency.config.constants.UserConstrains;
import com.gmail.mistle.ibo.travelagency.model.User;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;
import com.gmail.mistle.ibo.travelagency.service.UserService;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (user.getPassword().length() < UserConstrains.PASSWORD_MIN_LENGTH) {
            errors.rejectValue("password", "error.user_password.too_short");
        }
        if (emailAlreadyTaken(user.getEmail())) {
            errors.rejectValue("email", "error.user_email.already_taken");
        }
    }

    private boolean emailAlreadyTaken(String email) {
        try {
            userService.getByEmail(email);
            return true;
        }
        catch (NotFoundException ignored) {}
        return false;
    }

}
