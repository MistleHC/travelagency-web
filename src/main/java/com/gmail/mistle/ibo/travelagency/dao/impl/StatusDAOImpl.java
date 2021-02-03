package com.gmail.mistle.ibo.travelagency.dao.impl;

import com.gmail.mistle.ibo.travelagency.dao.StatusDAO;
import com.gmail.mistle.ibo.travelagency.model.Status;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class StatusDAOImpl implements StatusDAO {
    private final SessionFactory sessionFactory;

    @Override
    public Optional<Status> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select s from Status s where s.id = :id ", Status.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}
