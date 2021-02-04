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
public class CountryLogAspect {

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.CountryServiceImpl.getAll(..))")
    public void getAll() {
        log.info("List of countries was received from DB");
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.CountryServiceImpl.existsByName(..))")
    public void existsByName(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        String countryName = (String) lArgs[0];
        log.info(countryName + "was checked on existence");
    }
}
