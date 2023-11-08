package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.liga.domain.entity.restaurantService.item.Item;
import ru.liga.domain.exception.ItemNotFoundException;
import ru.liga.repository.ItemRepository;
import ru.liga.service.ItemService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item getItemById(long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Позиции с id = " + id + " не существует."));
    }
}
