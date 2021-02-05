package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.TourType;
import org.springframework.data.repository.CrudRepository;

public interface TourTypeDAO extends CrudRepository<TourType, Long> {
    TourType getByName(String name);
}
