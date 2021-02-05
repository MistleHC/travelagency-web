package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.TourDAO;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;
import com.gmail.mistle.ibo.travelagency.model.Tour;
import com.gmail.mistle.ibo.travelagency.service.TourService;
import com.gmail.mistle.ibo.travelagency.web.dto.TourFilterDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TourServiceImpl implements TourService {
    private final TourDAO tourDAO;

    @Override
    public List<Tour> getAll() {
        return StreamSupport.stream(tourDAO.findAll().spliterator(), false)
                                           .collect(Collectors.toList());
    }

    @Override
    public Tour getById(long tourId) {
        return tourDAO.findById(tourId)
                      .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Tour> getAllByFilter(TourFilterDto filter) {
        List<Tour> tours;

        if (filter.getHotel().equals("all") && (!filter.getCountry().equals("") && !filter.getCountry().equals("all"))){
            tours = tourDAO.findAllByCountry(filter.getCountry());
        } else if (filter.getCountry().equals("all") && (!filter.getHotel().equals("") && !filter.getHotel().equals("all"))) {
            tours = tourDAO.findAllByHotelType_Name(filter.getHotel());
        } else if (!filter.getCountry().equals("") && !filter.getCountry().equals("all") && !filter.getHotel().equals("")) {
            tours = tourDAO.findAllByCountryAndHotelType_Name(filter.getCountry(), filter.getHotel());
        } else {
            tours = getAll();
        }

        if (!filter.getLowerPrice().equals("")) {
            tours = tours.stream().filter(t -> t.getPrice() > Integer.parseInt(filter.getLowerPrice())).collect(Collectors.toList());
        }
        if (!filter.getHigherPrice().equals("")) {
            tours = tours.stream().filter(t -> t.getPrice() < Integer.parseInt(filter.getHigherPrice())).collect(Collectors.toList());
        }
        if (!filter.getLowerGroup().equals("")) {
            tours = tours.stream().filter(t -> t.getPeoples() < Integer.parseInt(filter.getLowerGroup())).collect(Collectors.toList());
        }
        tours.sort((a, b) -> Boolean.compare(a.isHot(), b.isHot()));
        Collections.reverse(tours);

        return tours;
    }

    @Override
    public void setHot(Long tourId) {
        tourDAO.setHot(tourId, true);
    }

    @Override
    public void setNotHot(Long tourId) {
        tourDAO.setHot(tourId, false);
    }

    @Override
    public void deleteById(Long tourId) {
        tourDAO.deleteById(tourId);
    }
}
