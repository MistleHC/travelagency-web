package com.gmail.mistle.ibo.travelagency.web.controller;

import com.gmail.mistle.ibo.travelagency.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
@AllArgsConstructor
public class ManagerController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ModelAndView getManagementDetails() {
        ModelAndView modelAndView = new ModelAndView("management");
        modelAndView.addObject("orders", orderService.getNewOrders());
        return modelAndView;
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('MANAGER')")
    public String deleteOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        if (orderId != null) {
            orderService.deleteOrder(Long.parseLong(orderId));
        }

        return "redirect:/manage";
    }

    @GetMapping("/paid")
    @PreAuthorize("hasRole('MANAGER')")
    public String setPaidOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        if (orderId != null) {
            orderService.setPaid(Long.parseLong(orderId));
        }

        return "redirect:/manage";
    }

    @GetMapping("/decline")
    @PreAuthorize("hasRole('MANAGER')")
    public String setDeclinedOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        if (orderId != null) {
            orderService.setDecline(Long.parseLong(orderId));
        }

        return "redirect:/manage";
    }
}
