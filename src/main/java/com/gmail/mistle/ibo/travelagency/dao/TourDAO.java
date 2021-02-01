package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDAO {
    List<Tour> findAll();
    List<Tour> findAllByCountry(String country);
    List<Tour> findAllByName(String name);
    Optional<Tour> findById(Long id);
    void save(Tour hotel);
    void deleteById(Long id);
}
