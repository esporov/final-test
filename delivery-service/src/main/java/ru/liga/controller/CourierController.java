package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.liga.domain.exception.ExceptionBody;
import ru.liga.service.CourierService;
import ru.liga.service.impl.CourierServiceImpl;
import ru.liga.web.dto.delivery.CourierDto;
import ru.liga.web.mapper.delivery.CourierMapper;

import java.util.List;

@Tag(name = "Courier controller")
@RestController
@RequestMapping("/delivery-service")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CourierController {

    private final Logger LOGGER = LoggerFactory.getLogger(CourierController.class);

    private final CourierService courierService;
    private final CourierMapper courierMapper;

    @Operation(summary = "Получить курьера по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @GetMapping("/v1.0/courier/id/{id}")
    public ResponseEntity<CourierDto> getCourierByCourierId(@PathVariable("id") long id) {
        LOGGER.info("Поиск курьера с id = {}", id);
        var courier = courierService.getCourierByCourierId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(courierMapper.toDto(courier));
    }

    @Operation(summary = "Получить список курьеров по статусу.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @GetMapping("/v1.0/couriers/status/{status}")
    public ResponseEntity<List<CourierDto>> getAllCourierByCourierStatus(@PathVariable("status") String courierStatus) {
        LOGGER.info("Поиск курьеров со статусом = {}", courierStatus);
        var couriers = courierService.getAllCourierByCourierStatus(courierStatus);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(courierMapper.toDto(couriers));
    }

    @Operation(summary = "Обновить статус курьера.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @PatchMapping("/v1.0/courier/{id}/status/{status}")
    public ResponseEntity<CourierDto> updateCourierStatusByCourierId(@PathVariable("id") long id,
                                                                     @PathVariable("status") String courierStatus) {
        LOGGER.info("Обновление статуса курьера на = {}", courierStatus);
        var courier = courierService.updateCourierStatusByCourierId(id, courierStatus);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(courierMapper.toDto(courier));
    }

}
