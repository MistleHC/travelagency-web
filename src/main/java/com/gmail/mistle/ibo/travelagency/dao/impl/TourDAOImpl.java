package com.gmail.mistle.ibo.travelagency.dao.impl;

import com.gmail.mistle.ibo.travelagency.dao.TourDAO;
import com.gmail.mistle.ibo.travelagency.model.Tour;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TourDAOImpl implements TourDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<Tour> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select t from Tour t", Tour.class)
                .list();
    }

    @Override
    public List<Tour> findAllByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();

        return session
                .createQuery("select t from Tour t where t.country = :country", Tour.class)
                .setParameter("country", country)
                .list();
    }

    @Override
    public List<Tour> findAllByHotel(String hotel) {
        Session session = sessionFactory.getCurrentSession();

        return session
                .createQuery("select t from Tour t where t.hotelType.name = :hotel", Tour.class)
                .setParameter("hotel", hotel)
                .list();
    }

    @Override
    public List<Tour> findAllByCountryAndHotel(String country, String hotel) {
        Session session = sessionFactory.getCurrentSession();

        return session
                .createQuery("select t from Tour t where t.country = :country and t.hotelType.name = :hotel", Tour.class)
                .setParameter("country", country)
                .setParameter("hotel", hotel)
                .list();
    }

    @Override
    public List<Tour> findAllByName(String name) {
        return null;
    }

    @Override
    public Optional<Tour> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select t from Tour t where t.id = :id ", Tour.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public void save(Tour hotel) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
