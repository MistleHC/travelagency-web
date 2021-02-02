package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.TourDAO;
import com.gmail.mistle.ibo.travelagency.model.Tour;
import com.gmail.mistle.ibo.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Tour> getAllByFilter(String country, String hotel, String lowerPrice, String higherPrice, String lowerGroup) {
        List<Tour> tours;

        if (hotel.equals("all") && (!country.equals("") && !country.equals("all"))){
            tours = tourDAO.findAllByCountry(country);
        } else if (country.equals("all") && (!hotel.equals("") && !hotel.equals("all"))) {
            tours = tourDAO.findAllByHotel(hotel);
        } else if (!country.equals("") && !country.equals("all") && !hotel.equals("")) {
            tours = tourDAO.findAllByCountryAndHotel(country, hotel);
        } else {
            tours = getAll();
        }
        log.info("Tours were received from DB");

        if (!lowerPrice.equals("")) {
            tours = tours.stream().filter(t -> t.getPrice() > Integer.parseInt(lowerPrice)).collect(Collectors.toList());
        }
        if (!higherPrice.equals("")) {
            tours = tours.stream().filter(t -> t.getPrice() < Integer.parseInt(higherPrice)).collect(Collectors.toList());
        }
        if (!lowerGroup.equals("")) {
            tours = tours.stream().filter(t -> t.getPeoples() < Integer.parseInt(lowerGroup)).collect(Collectors.toList());
        }
        log.info("Tours were filtered");

        return tours;
    }
}
