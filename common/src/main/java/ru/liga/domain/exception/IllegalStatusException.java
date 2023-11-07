package ru.liga.domain.exception;

public class IllegalStatusException extends IllegalArgumentException{
    public IllegalStatusException() {
    }

    public IllegalStatusException(String s) {
        super(s);
    }

    public IllegalStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
