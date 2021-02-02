package com.gmail.mistle.ibo.travelagency.web.controller;

import com.gmail.mistle.ibo.travelagency.model.Tour;
import com.gmail.mistle.ibo.travelagency.service.CountryService;
import com.gmail.mistle.ibo.travelagency.service.HotelService;
import com.gmail.mistle.ibo.travelagency.service.TourService;
import com.gmail.mistle.ibo.travelagency.web.dto.TourFilterDto;
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
    private final HotelService hotelService;

    @GetMapping
    public ModelAndView showAllTours(@RequestParam(value = "country", required = false) String country,
                                     @RequestParam(value = "hotel", required = false) String hotel,
                                     @RequestParam(value = "lowerprice", required = false) String lowerPrice,
                                     @RequestParam(value = "higherprice", required = false) String higherPrice,
                                     @RequestParam(value = "lowergroup", required = false) String lowerGroup) {
        ModelAndView modelAndView = new ModelAndView("home_page");

        TourFilterDto filter = TourFilterDto.builder()
                .country(country)
                .hotel(hotel)
                .lowerPrice(lowerPrice)
                .higherPrice(higherPrice)
                .lowerGroup(lowerGroup)
                .build();
        filter.changeDefaultValues();

        modelAndView.addObject("tours", tourService.getAllByFilter(filter));
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("hotels", hotelService.getAll());
        return modelAndView;
    }
}
