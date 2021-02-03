package com.gmail.mistle.ibo.travelagency.web.controller;

import com.gmail.mistle.ibo.travelagency.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String processTourOrder(@RequestParam(value = "tourid", required = true) String tourId) {
        if (tourId != null) {
            orderService.createOrder(Long.parseLong(tourId));
        }

        return "redirect:/profile";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        if (orderId != null) {
            orderService.deleteOrder(Long.parseLong(orderId));
        }

        return "redirect:/profile";
    }

    @GetMapping("/confirm")
    public String confirmOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        if (orderId != null) {
            orderService.deleteOrder(Long.parseLong(orderId));
        }

        return "redirect:/profile";
    }

}
