package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.HotelDAO;
import com.gmail.mistle.ibo.travelagency.dao.TourDAO;
import com.gmail.mistle.ibo.travelagency.dao.TourTypeDAO;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;
import com.gmail.mistle.ibo.travelagency.exceptions.ValidationException;
import com.gmail.mistle.ibo.travelagency.model.Tour;
import com.gmail.mistle.ibo.travelagency.model.TourType;
import com.gmail.mistle.ibo.travelagency.service.TourService;
import com.gmail.mistle.ibo.travelagency.web.dto.TourCreationDto;
import com.gmail.mistle.ibo.travelagency.web.dto.TourFilterDto;
import com.gmail.mistle.ibo.travelagency.web.validator.TourCreationValidator;
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
    private final TourTypeDAO tourTypeDAO;
    private final HotelDAO hotelDAO;

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

        if (filter.getHotel().equals("") && !filter.getCountry().equals("")){
            tours = tourDAO.findAllByCountry(filter.getCountry());
        } else if (filter.getCountry().equals("") && !filter.getHotel().equals("")) {
            tours = tourDAO.findAllByHotelType_Name(filter.getHotel());
        } else if (!filter.getCountry().equals("") && !filter.getCountry().equals("")) {
            tours = tourDAO.findAllByCountryAndHotelType_Name(filter.getCountry(), filter.getHotel());
        } else {
            tours = getAll();
        }

        return filterTours(tours, filter);
    }

    @Override
    public List<TourType> getTourTypes() {
        return StreamSupport.stream(tourTypeDAO.findAll().spliterator(), false)
                                               .collect(Collectors.toList());
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

    @Override
    public void setDiscount(Long tourId, Long discount) {
        Tour tour = tourDAO.findById(tourId).orElseThrow(NotFoundException::new);
        tour.setDiscount(discount);
        tourDAO.save(tour);
    }

    @Override
    @Transactional
    public Tour saveNewTour(TourCreationDto tourCreationDto) {
        try {
            TourCreationValidator.validate(tourCreationDto);
        } catch (ValidationException ex) {
            log.error("Tour creation validation exception. Error: " + ex.getMessage());
            return null;
        }

        Tour tour = Tour.builder()
                .name(tourCreationDto.getTourName())
                .description(tourCreationDto.getTourDescription())
                .country(tourCreationDto.getTourCountry())
                .peoples(tourCreationDto.getTourSize())
                .price(tourCreationDto.getTourPrice())
                .isHot(false)
                .hotelType(hotelDAO.getByName(tourCreationDto.getTourHotel()))
                .tourType(tourTypeDAO.getByName(tourCreationDto.getTourType()))
                .build();

        return tourDAO.save(tour);
    }

    private List<Tour> filterTours(List<Tour> list, TourFilterDto filter) {
        List<Tour> tours = list;
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
}
