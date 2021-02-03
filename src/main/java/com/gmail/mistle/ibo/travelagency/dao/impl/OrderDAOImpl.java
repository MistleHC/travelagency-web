package com.gmail.mistle.ibo.travelagency.dao.impl;

import com.gmail.mistle.ibo.travelagency.dao.OrderDAO;
import com.gmail.mistle.ibo.travelagency.model.Order;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderDAOImpl implements OrderDAO {
    private final SessionFactory sessionFactory;

    @Override
    public void save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select o from Order o where o.customer.id = :userId", Order.class)
                .setParameter("userId", userId)
                .list();
    }
}
