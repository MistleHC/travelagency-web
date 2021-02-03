package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(long tourId);
    List<Order> getAllByUserId(Long userId);
}
