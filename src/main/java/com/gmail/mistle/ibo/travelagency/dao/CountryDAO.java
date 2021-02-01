package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDAO {
    List<Country> findAll();
    Optional<Country> findByName(String countryName);
    void save(Country countryToSave);
}
