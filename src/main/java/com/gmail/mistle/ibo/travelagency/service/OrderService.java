package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(long tourId);
    void deleteOrder(long orderId);
    void setPaid(long orderId);
    void setDecline(long orderId);
    List<Order> getAllByUserId(Long userId);
    List<Order> getNewOrders();
}
