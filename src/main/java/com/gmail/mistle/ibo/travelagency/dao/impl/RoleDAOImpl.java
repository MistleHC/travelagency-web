package com.gmail.mistle.ibo.travelagency.dao.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.gmail.mistle.ibo.travelagency.dao.RoleDAO;
import com.gmail.mistle.ibo.travelagency.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleDAOImpl implements RoleDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select r from Role r",  Role.class)
                .list();
    }

    @Override
    public Optional<Role> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session
                    .createQuery("select r from Role r where r.name = :name", Role.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
    }

    @Override
    public void save(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(role);
    }

}
