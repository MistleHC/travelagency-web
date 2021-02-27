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

/**
 * Tour interaction interface
 */
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TourServiceImpl implements TourService {
    private final TourDAO tourDAO;
    private final TourTypeDAO tourTypeDAO;
    private final HotelDAO hotelDAO;

    /**
     * @return List of tour objects
     */
    @Override
    public List<Tour> getAll() {
        return StreamSupport.stream(tourDAO.findAll().spliterator(), false)
                                           .collect(Collectors.toList());
    }

    /**
     * @param tourId - target id
     * @return Tour object
     */
    @Override
    public Tour getById(long tourId) {
        return tourDAO.findById(tourId)
                      .orElseThrow(NotFoundException::new);
    }

    /**
     * @param filter contains filtering parameters
     * @return list of filtered tours
     */
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

    /**
     * @return the list of tour types entities
     * @see TourType
     */
    @Override
    public List<TourType> getTourTypes() {
        return StreamSupport.stream(tourTypeDAO.findAll().spliterator(), false)
                                               .collect(Collectors.toList());
    }

    /**
     * @param tourId target id
     * Mark target object's field is_hot as true
     */
    @Override
    public void setHot(Long tourId) {
        tourDAO.setHot(tourId, true);
    }

    /**
     * @param tourId targed id
     * Mark target object's field is_hot as false
     */
    @Override
    public void setNotHot(Long tourId) {
        tourDAO.setHot(tourId, false);
    }

    /**
     * @param tourId Delete target object using ID
     */
    @Override
    public void deleteById(Long tourId) {
        tourDAO.deleteById(tourId);
    }

    /**
     * @param tourId ID of target object
     * @param discount Discount value that ranges [1 - 20]
     */
    @Override
    public void setDiscount(Long tourId, Long discount) {
        Tour tour = tourDAO.findById(tourId).orElseThrow(NotFoundException::new);
        tour.setDiscount(discount);
        tourDAO.save(tour);
    }

    /**
     * Creating new tour
     * @param tourCreationDto Data transfer object that contains initial tour data
     * @return created Tour object
     */
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


    /**
     * @param list List of the Tour objects
     * @param filter filtering parameters
     * @return sorted list of Tour objects
     */
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
