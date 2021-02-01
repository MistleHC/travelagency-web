package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.TourDAO;
import com.gmail.mistle.ibo.travelagency.model.Tour;
import com.gmail.mistle.ibo.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TourServiceImpl implements TourService {
    private final TourDAO tourDAO;

    @Override
    public List<Tour> getAll() {
        List<Tour> tours = tourDAO.findAll();
        log.info("All tours were received from DB");
        return tours;
    }

    @Override
    public List<Tour> getAllByCountry(String country) {
        List<Tour> toursByCountry = tourDAO.findAllByCountry(country);
        log.info(country + " tours were received from DB");
        return toursByCountry;
    }
}
