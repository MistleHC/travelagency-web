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
public class UserLogAspect {

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.UserServiceImpl.getAllByRole(..))")
    public void getAllByRole(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        String role = (String) lArgs[0];
        log.info("Fetched all users by role {}", role);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.UserServiceImpl.getById(..))")
    public void getById(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        long id = (long) lArgs[0];
        log.info("Gotten user by id {}", id);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.UserServiceImpl.getByEmail(..))")
    public void getByEmail(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        String email = (String) lArgs[0];
        log.info("Gotten user by email {}", email);
    }

    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.UserServiceImpl.save(..))")
    public void getAllByRole() {
        log.info("Saved user");
    }
}
