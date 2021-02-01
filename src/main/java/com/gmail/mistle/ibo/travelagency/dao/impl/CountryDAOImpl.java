package com.gmail.mistle.ibo.travelagency.dao.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.gmail.mistle.ibo.travelagency.dao.CountryDAO;
import com.gmail.mistle.ibo.travelagency.model.Country;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CountryDAOImpl implements CountryDAO {
    private final SessionFactory sessionFactory;

    @Override
    public void save(Country countryToSave) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(countryToSave);
    }

    @Override
    public List<Country> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select c from Country c", Country.class)
                .list();
    }

    @Override
    public Optional<Country> findByName(String countryName) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select c from Country c where c.name=:name", Country.class)
                .setParameter("name", countryName)
                .uniqueResultOptional();
    }
}
