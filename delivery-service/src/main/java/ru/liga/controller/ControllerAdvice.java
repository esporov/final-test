package ru.liga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.liga.domain.exception.CourierNotFoundException;
import ru.liga.domain.exception.ExceptionBody;
import ru.liga.domain.exception.IllegalStatusException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CourierNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleCourierNotFound(CourierNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(IllegalStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIllegalStatus(IllegalStatusException e) {
        return new ExceptionBody(e.getMessage());
    }

}
