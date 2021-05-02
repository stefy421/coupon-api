package com.meli.api.coupon.service;

import com.meli.api.coupon.dto.ItemDTO;
import com.meli.api.coupon.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Service to access to the item repository
 *
 * @author stefanny.rodriguez
 *
 */
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Method to find the items by id
     * @param items The set of the user's favorite item ids.
     * @return {@link ItemDTO} Item list, which is composed of the item id and its price.
     */
    public List<ItemDTO> findItemsById(List<String> items){
        return itemRepository.findItemsByIds(new HashSet<>(items));
    }
}
