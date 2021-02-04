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
public class HotelLogAspect {

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.HotelServiceImpl.getAll(..))")
    public void getAll() {
        log.info("List of hotels is received from DB");
    }
}
