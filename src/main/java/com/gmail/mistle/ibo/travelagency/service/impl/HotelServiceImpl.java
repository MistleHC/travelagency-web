package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.HotelDAO;
import com.gmail.mistle.ibo.travelagency.model.HotelType;
import com.gmail.mistle.ibo.travelagency.service.HotelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelDAO hotelDAO;

    /**
     * @return list of HotelType objects
     * @see HotelType
     */
    @Override
    public List<HotelType> getAll() {
        return StreamSupport.stream(hotelDAO.findAll().spliterator(), false)
                                            .collect(Collectors.toList());
    }
}
