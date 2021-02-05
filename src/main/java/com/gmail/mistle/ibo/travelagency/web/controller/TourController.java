package com.gmail.mistle.ibo.travelagency.web.controller;

import com.gmail.mistle.ibo.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/tour")
@AllArgsConstructor
public class TourController {
    TourService tourService;

    @GetMapping("/hot")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String setAsHot(@RequestParam(value = "tourid", required = true) String tourId) {
        tourService.setHot(Long.parseLong(tourId));

        return "redirect:/";
    }

    @GetMapping("/de-hot")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String setAsNotHot(@RequestParam(value = "tourid", required = true) String tourId) {
        tourService.setNotHot(Long.parseLong(tourId));

        return "redirect:/";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTour(@RequestParam(value = "tourid", required = true) String tourId) {
        tourService.deleteById(Long.parseLong(tourId));

        return "redirect:/";
    }
}
