package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.HotelType;
import org.springframework.data.repository.CrudRepository;

public interface HotelDAO extends CrudRepository<HotelType, Long> {
    HotelType getByName(String name);
}
