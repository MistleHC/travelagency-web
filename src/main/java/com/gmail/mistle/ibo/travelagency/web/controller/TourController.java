package com.gmail.mistle.ibo.travelagency.web.controller;

import com.gmail.mistle.ibo.travelagency.service.TourService;
import com.gmail.mistle.ibo.travelagency.web.dto.TourCreationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tour")
@AllArgsConstructor
@Slf4j
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

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createTour(TourCreationDto tourCreationDto) {
        tourService.saveNewTour(tourCreationDto);

        return "redirect:/";
    }

    @PostMapping("/discount")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String createTour(@RequestParam(value = "tourid", required = true) Long tourId,
                             @RequestParam(value = "tourDiscount", required = true) Long discount) {

        if (discount <= 20 && discount > 0) {
            tourService.setDiscount(tourId, discount);
        }

        return "redirect:/";
    }
}
