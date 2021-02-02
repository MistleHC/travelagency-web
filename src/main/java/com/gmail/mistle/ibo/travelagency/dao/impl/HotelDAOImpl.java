package com.gmail.mistle.ibo.travelagency.dao.impl;

import com.gmail.mistle.ibo.travelagency.dao.HotelDAO;
import com.gmail.mistle.ibo.travelagency.model.HotelType;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class HotelDAOImpl implements HotelDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<HotelType> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select h from HotelType h", HotelType.class)
                .list();
    }
}
