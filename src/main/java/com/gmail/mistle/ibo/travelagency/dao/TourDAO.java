package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Tour;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TourDAO extends CrudRepository<Tour, Long> {
    List<Tour> findAllByCountry(String country);
    List<Tour> findAllByHotelType_Name(String hotel);
    List<Tour> findAllByCountryAndHotelType_Name(String country, String hotel);
    Optional<Tour> findById(Long id);
}
