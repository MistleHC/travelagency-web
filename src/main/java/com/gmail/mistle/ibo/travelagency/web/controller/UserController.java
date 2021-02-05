package com.gmail.mistle.ibo.travelagency.web.controller;

import com.gmail.mistle.ibo.travelagency.config.security.CustomUserDetails;
import com.gmail.mistle.ibo.travelagency.service.OrderService;
import com.gmail.mistle.ibo.travelagency.service.UserService;
import com.gmail.mistle.ibo.travelagency.web.dto.UserInfoDto;
import com.gmail.mistle.ibo.travelagency.web.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public ModelAndView getCustomerDetails() {
        ModelAndView modelAndView = new ModelAndView("customer_details");
        UserInfoDto customer = userService.getUserInfo(getIdOfCurrentLoggedInUser());
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("orders", orderService.getAllByUserId(getIdOfCurrentLoggedInUser()));
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editProfile(UserProfileDto userProfileDto) {
        userService.updateUserInfo(userProfileDto, getIdOfCurrentLoggedInUser());

        return "redirect:/profile";
    }

    private Long getIdOfCurrentLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        return userDetails.getUserId();
    }
}
