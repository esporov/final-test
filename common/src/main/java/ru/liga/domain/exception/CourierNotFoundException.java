package ru.liga.domain.exception;

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException() {
    }

    public CourierNotFoundException(String message) {
        super(message);
    }

    public CourierNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
