package com.meli.api.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * This DTO represents the API response, which is composed of the suggested list of items to purchase
 * and the total purchase.
 *
 * @author stefanny.rodriguez
 *
 */
@Getter
@AllArgsConstructor
public class ResponseDTO {
    private List<String> item_ids;
    private Float total;

}
