package com.gmail.mistle.ibo.travelagency.service;

import com.gmail.mistle.ibo.travelagency.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAll();
    boolean existsByName(String countryName);
}
