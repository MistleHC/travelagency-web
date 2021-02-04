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
public class RoleLogAspect {

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.RoleServiceImpl.getAll(..))")
    public void getAll() {
        log.info("Fetched all roles");
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.RoleServiceImpl.getByName(..))")
    public void getByName(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        String name = (String) lArgs[0];
        log.info("Gotten role by name {}", name);
    }

}
