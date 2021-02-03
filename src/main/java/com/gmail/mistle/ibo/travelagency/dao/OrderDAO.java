package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    void save(Order order);
    void deleteById(Long id);
    Optional<Order> findById(Long id);
    List<Order> findAllByUserId(Long userId);
    List<Order> findNewOrders();
}
