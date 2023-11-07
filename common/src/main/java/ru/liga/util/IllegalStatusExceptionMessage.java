package ru.liga.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IllegalStatusExceptionMessage {
    default <E extends Enum<E>> String exceptionMessage(Class<E> e) {
        String enums = Stream.of(e.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining(", ", "", "."));
        return String.format("Указан неверный статус. Возможны варианты: %s Операция прервана.", enums);
    }
}
