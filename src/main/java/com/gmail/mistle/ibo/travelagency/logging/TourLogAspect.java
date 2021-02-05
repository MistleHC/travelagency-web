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
public class TourLogAspect {

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.TourServiceImpl.getAll(..))")
    public void getAll() {
        log.info("All tours were received from DB");
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.TourServiceImpl.getAllByFilter(..))")
    public void getAllByFilter() {
        log.info("Tours were filtered");
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.TourServiceImpl.setHot(..))")
    public void setHot(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        long id = (long) lArgs[0];
        log.info("Tour {} was marked as hot", id);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.TourServiceImpl.setNotHot(..))")
    public void setNotHot(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        long id = (long) lArgs[0];
        log.info("Tour {} was marked as NOT hot", id);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.TourServiceImpl.deleteById(..))")
    public void deleteById(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        long id = (long) lArgs[0];
        log.info("Tour {} was deleted", id);
    }

}
