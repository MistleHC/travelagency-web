package com.gmail.mistle.ibo.travelagency.logging;

import com.gmail.mistle.ibo.travelagency.model.User;
import com.gmail.mistle.ibo.travelagency.service.AuthService;
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
public class AuthLogsAspect {
    AuthService authService;


    @After("execution(* com.gmail.mistle.ibo.travelagency.service.impl.AuthServiceImpl.registerUser(..))")
    public void registerUser(JoinPoint joinPoint) {
        Object[] lArgs = joinPoint.getArgs();
        User user = (User) lArgs[0];
        log.info("User has been registered: " + user.getName());
    }
}
