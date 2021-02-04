package com.gmail.mistle.ibo.travelagency.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class OrdersLogAspect {

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.OrderServiceImpl.createOrder(..))")
    void createOrder() {
        log.info("Order was saved");
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.OrderServiceImpl.deleteOrder(..))")
    void deleteOrder(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        long orderId = (long) lArgs[0];
        log.info("Order with id {} was deleted", orderId);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.OrderServiceImpl.setPaid(..))")
    void setPaid(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        long orderId = (long) lArgs[0];
        log.info("Order with id {} was paid", orderId);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.OrderServiceImpl.setDecline(..))")
    void setDecline(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        long orderId = (long) lArgs[0];
        log.info("Order with id {} was declined", orderId);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.OrderServiceImpl.getAllByUserId(..))")
    void getAllByUserId(JoinPoint joinPoint){
        Object[] lArgs = joinPoint.getArgs();
        long userId = (long) lArgs[0];
        log.info("List of orders was received from DB for user {}", userId);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.OrderServiceImpl.getNewOrders(..))")
    void getNewOrders(){
        log.info("List of new orders was received from DB");
    }
}
