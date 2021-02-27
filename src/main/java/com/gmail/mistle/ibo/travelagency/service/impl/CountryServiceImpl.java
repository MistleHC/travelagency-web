package com.gmail.mistle.ibo.travelagency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.mistle.ibo.travelagency.dao.CountryDAO;
import com.gmail.mistle.ibo.travelagency.model.Country;
import com.gmail.mistle.ibo.travelagency.service.CountryService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryDAO countryDAO;

    /**
     * @return list of Country objects
     * @see Country
     */
    @Override
    public List<Country> getAll() {
        return StreamSupport.stream(countryDAO.findAll().spliterator(), false)
                                              .collect(Collectors.toList());
    }

    /**
     * @param countryName target country name
     * @return existence of target country
     * @see Country
     */
    @Override
    public boolean existsByName(String countryName) {
        return countryDAO.findByName(countryName).isPresent();
    }
}
