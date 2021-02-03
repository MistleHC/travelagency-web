package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Order;

import java.util.List;

public interface OrderDAO {
    void save(Order order);
    List<Order> findAllByUserId(Long userId);
}
