package ru.liga.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.domain.entity.restaurantService.item.Item;
import ru.liga.domain.exception.ExceptionBody;
import ru.liga.service.ItemService;
import ru.liga.web.dto.restaurant.ItemDto;
import ru.liga.web.mapper.kitchen.ItemMapper;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Item Controller")
@RestController
@RequestMapping("/restaurant-api")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @Operation(summary = "Поиск блюда по id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @GetMapping("/v1.0/item-feign/id/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") long itemId) {
        LOGGER.info("Ищем позицию меню по id = {}",itemId);
        var item = itemService.getItemById(itemId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(item);
    }
}
