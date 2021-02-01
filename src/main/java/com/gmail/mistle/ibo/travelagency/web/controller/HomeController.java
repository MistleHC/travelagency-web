package com.gmail.mistle.ibo.travelagency.web.controller;

import com.gmail.mistle.ibo.travelagency.model.Tour;
import com.gmail.mistle.ibo.travelagency.service.CountryService;
import com.gmail.mistle.ibo.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    private final TourService tourService;
    private final CountryService countryService;

    @GetMapping
    public ModelAndView showAllTours(@RequestParam(value = "country", required = false) String country) {
        ModelAndView modelAndView = new ModelAndView("home_page");
        modelAndView.addObject("tours", findToursByCountry(country));
        modelAndView.addObject("countries", countryService.getAll());
        return modelAndView;
    }

    private List<Tour> findToursByCountry(String country) {
        if (country == null) {
            return tourService.getAll();
        }
        return tourService.getAllByCountry(country);
    }
}
