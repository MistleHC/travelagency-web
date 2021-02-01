package com.gmail.mistle.ibo.travelagency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.mistle.ibo.travelagency.dao.CountryDAO;
import com.gmail.mistle.ibo.travelagency.model.Country;
import com.gmail.mistle.ibo.travelagency.service.CountryService;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryDAO countryDAO;

    @Override
    public List<Country> getAll() {
        List<Country> countries = countryDAO.findAll();
        log.info("List of countries is received from DB");
        return countries;
    }

    @Override
    public boolean existsByName(String countryName) {
        boolean existsByCountryName = countryDAO.findByName(countryName).isPresent();
        log.info(countryName + "exists in DB status: " + existsByCountryName);
        return existsByCountryName;
    }
}
