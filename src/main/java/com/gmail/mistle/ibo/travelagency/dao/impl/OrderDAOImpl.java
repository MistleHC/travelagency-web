package com.gmail.mistle.ibo.travelagency.dao.impl;

import com.gmail.mistle.ibo.travelagency.dao.OrderDAO;
import com.gmail.mistle.ibo.travelagency.model.Order;
import com.gmail.mistle.ibo.travelagency.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Order r where r.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Optional<Order> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select o from Order o where o.id=:id", Order.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select o from Order o where o.customer.id = :userId", Order.class)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public List<Order> findNewOrders() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select o from Order o where o.status.title = :title", Order.class)
                .setParameter("title", "Pending")
                .list();
    }
}
