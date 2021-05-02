package com.meli.api.coupon.repository;

import com.meli.api.coupon.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.annotations.Cacheable;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This repository handles the MercadoLibre API calls to get the items and its prices.
 * This information is cached in order to access it more efficiently.
 *
 * @author stefanny.rodriguez
 *
 */
@Repository
public class ItemRepository {

    private final RestTemplate restTemplate;

    public ItemRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${mercadolibre.api}")
    private String mercadolibreAPI;

    /**
     * Method to query the user's favorite items prices from MercadoLibre API
     *
     * @param items The set of the user's favorite item ids.
     * @return {@link ItemDTO} Item list, which is composed of the item id and its price.
     *
     */
    @Cacheable("items")
    public List<ItemDTO> findItemsByIds(Set<String> items){
        return items
                .stream()
                .map(itemId -> restTemplate.getForObject(getUriForItemId(itemId), ItemDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Method to dynamically make the URI with every item id
     * @param itemId item id
     * @return URI for the item id
     *
     */
    private URI getUriForItemId(String itemId) {
        return UriComponentsBuilder.fromUriString(mercadolibreAPI)
                .pathSegment("/items/")
                .pathSegment(itemId)
                .build()
                .toUri();
    }
}
