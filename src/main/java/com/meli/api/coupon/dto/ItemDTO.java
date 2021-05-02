package com.meli.api.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This DTO represents an item, which is composed of the item id and its price.
 *
 * @author stefanny.rodriguez
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String id;
    private Float price;
}
