package ru.liga.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.liga.domain.exception.ExceptionBody;
import ru.liga.domain.exception.IllegalStatusException;
import ru.liga.domain.exception.ItemNotFoundException;
import ru.liga.domain.exception.RestaurantNotFoundException;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    private final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(RestaurantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleRestaurantNotFound(RestaurantNotFoundException e) {
        LOGGER.warn(e.getMessage(),e);
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleItemNotFound(ItemNotFoundException e) {
        LOGGER.warn(e.getMessage(),e);
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(IllegalStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIllegalStatus(IllegalStatusException e) {
        LOGGER.warn(e.getMessage(),e);
        return new ExceptionBody(e.getMessage());
    }
}
