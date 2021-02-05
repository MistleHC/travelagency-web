package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.model.Tour;
import com.gmail.mistle.ibo.travelagency.model.TourType;
import com.gmail.mistle.ibo.travelagency.web.dto.TourCreationDto;
import com.gmail.mistle.ibo.travelagency.web.dto.TourFilterDto;

import java.util.List;

public interface TourService {
    List<Tour> getAll();
    Tour getById(long tourId);
    List<Tour> getAllByFilter(TourFilterDto filter);
    List<TourType> getTourTypes();
    void setHot(Long tourId);
    void setNotHot(Long tourId);
    void deleteById(Long tourId);
    Tour saveNewTour(TourCreationDto tourCreationDto);
}
