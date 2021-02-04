package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDAO extends CrudRepository<Order, Long>, JpaRepository<Order, Long> {

    List<Order> findAllByStatus_Title(String statusTitle);

    List<Order> findAllByCustomer_Id(Long customerId);
}
