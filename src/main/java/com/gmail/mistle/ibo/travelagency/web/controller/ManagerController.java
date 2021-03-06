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
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ModelAndView getManagementDetails() {
        ModelAndView modelAndView = new ModelAndView("management");
        modelAndView.addObject("orders", orderService.getNewOrders());
        return modelAndView;
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String deleteOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        orderService.deleteOrder(Long.parseLong(orderId));

        return "redirect:/manage";
    }

    @GetMapping("/paid")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String setPaidOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        orderService.setPaid(Long.parseLong(orderId));

        return "redirect:/manage";
    }

    @GetMapping("/decline")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String setDeclinedOrder(@RequestParam(value = "orderid", required = true) String orderId) {
        orderService.setDecline(Long.parseLong(orderId));

        return "redirect:/manage";
    }
}
