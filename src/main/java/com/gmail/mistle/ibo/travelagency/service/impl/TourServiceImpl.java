package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.TourDAO;
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
    public List<Tour> getAllByFilter(TourFilterDto filter) {
        List<Tour> tours;
        log.info(filter.toString());

        if (filter.getHotel().equals("all") && (!filter.getCountry().equals("") && !filter.getCountry().equals("all"))){
            tours = tourDAO.findAllByCountry(filter.getCountry());
        } else if (filter.getCountry().equals("all") && (!filter.getHotel().equals("") && !filter.getHotel().equals("all"))) {
            tours = tourDAO.findAllByHotel(filter.getHotel());
        } else if (!filter.getCountry().equals("") && !filter.getCountry().equals("all") && !filter.getHotel().equals("")) {
            tours = tourDAO.findAllByCountryAndHotel(filter.getCountry(), filter.getHotel());
        } else {
            tours = getAll();
        }
        log.info("Tours were received from DB");

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
        log.info("Tours were filtered");
        log.info(tours.toString());
        return tours;
    }
}
