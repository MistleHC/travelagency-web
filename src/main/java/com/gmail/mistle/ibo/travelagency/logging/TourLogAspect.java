package com.gmail.mistle.ibo.travelagency.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

}
