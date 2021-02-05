package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Tour;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TourDAO extends CrudRepository<Tour, Long> {

    Optional<Tour> findById(Long id);

    List<Tour> findAllByCountry(String country);

    List<Tour> findAllByHotelType_Name(String hotel);

    List<Tour> findAllByCountryAndHotelType_Name(String country, String hotel);

    @Modifying
    @Query("update Tour t set t.isHot = :condition where t.id = :id")
    void setHot(@Param("id") Long id, @Param("condition") Boolean condition);
}
