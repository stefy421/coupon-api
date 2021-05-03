package com.meli.api.coupon.service;

import com.meli.api.coupon.dto.ItemDTO;

import java.util.Collections;
import java.util.List;

/**
 * Interface that exposes the methods for the items.
 *
 * @author stefanny.rodriguez
 *
 */
public interface IItemService {

    /**
     * Method to find the items by id
     *
     * @param items The set of the user's favorite item ids.
     * @return {@link ItemDTO} Item list, which is composed of the item id and its price.
     */
    default List<ItemDTO> findItemsById(List<String> items) {
        return Collections.emptyList();
    }
}
