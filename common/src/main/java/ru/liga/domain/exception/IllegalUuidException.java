package ru.liga.domain.exception;

public class IllegalUuidException extends RuntimeException {
    public IllegalUuidException() {
        super();
    }

    public IllegalUuidException(String message) {
        super(message);
    }

    public IllegalUuidException(String message, Throwable cause) {
        super(message, cause);
    }
}
