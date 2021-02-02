package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.HotelType;

import java.util.List;

public interface HotelDAO {
    List<HotelType> findAll();
}
