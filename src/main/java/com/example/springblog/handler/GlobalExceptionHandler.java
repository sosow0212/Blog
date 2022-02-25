package com.example.springblog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice // ?
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    private String handleArgumentException(Exception e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }
}
