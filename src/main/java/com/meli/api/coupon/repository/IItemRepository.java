package com.meli.api.coupon.repository;

import com.meli.api.coupon.dto.ItemDTO;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Interface that exposes the methods to handle the MercadoLibre API
 *
 * @author stefanny.rodriguez
 *
 */

public interface IItemRepository {

    /**
     * Method to query the user's favorite items prices from MercadoLibre API
     *
     * @param items The set of the user's favorite item ids.
     * @return {@link ItemDTO} Item list, which is composed of the item id and its price.
     */
    default List<ItemDTO> findItemsByIds(Set<String> items) {
        return Collections.emptyList();
    }
}
