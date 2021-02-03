package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.config.security.CustomUserDetails;
import com.gmail.mistle.ibo.travelagency.dao.OrderDAO;
import com.gmail.mistle.ibo.travelagency.model.Order;
import com.gmail.mistle.ibo.travelagency.service.OrderService;
import com.gmail.mistle.ibo.travelagency.service.StatusService;
import com.gmail.mistle.ibo.travelagency.service.TourService;
import com.gmail.mistle.ibo.travelagency.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final TourService tourService;
    private final StatusService statusService;
    private final OrderDAO orderDAO;

    @Override
    @Transactional
    public void createOrder(long tourId) {
        Order order = Order.builder()
                .customer(userService.getById(getIdOfCurrentLoggedInUser()))
                .tour(tourService.getById(tourId))
                .status(statusService.findById((long) 1))
                .build();
        log.info("Order object was initialized");

        orderDAO.save(order);
        log.info("Order was saved");
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {
        List<Order> usersOrders = orderDAO.findAllByUserId(userId);
        log.info("List of orders was received from DB for user {}", userId);
        return usersOrders;
    }

    private Long getIdOfCurrentLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        return userDetails.getUserId();
    }
}
