package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.HotelDAO;
import com.gmail.mistle.ibo.travelagency.model.HotelType;
import com.gmail.mistle.ibo.travelagency.service.HotelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelDAO hotelDAO;

    @Override
    public List<HotelType> getAll() {
        List<HotelType> hotels = hotelDAO.findAll();
        log.info("List of hotels is received from DB");
        return hotels;
    }
}
