package com.gmail.mistle.ibo.travelagency.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public String handleNotFoundException() {
        log.error("404 error occurred");
        return "not_found";
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public String handleNotAvailableEndpoint() {
        log.error("Request to not available endpoint");
        return "not_found";
    }

}
