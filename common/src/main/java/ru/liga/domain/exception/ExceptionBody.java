package ru.liga.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Schema(title = "Exception body",description = "Тело ошибки")
public class ExceptionBody {

    @Schema(description = "Сообщение ошибки")
    private String message;
    @Schema(description = "Список ошибок", nullable = true)
    private Map<String, String> errors;

    public ExceptionBody(String message){
        this.message = message;
    }

    public ExceptionBody(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
