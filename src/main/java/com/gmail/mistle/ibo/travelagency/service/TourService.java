package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.model.Tour;

import java.util.List;

public interface TourService {
    List<Tour> getAll();
    List<Tour> getAllByCountry(String country);
}
