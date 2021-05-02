package com.meli.api.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * This DTO represents the user request, which is composed of the list of items to check and the coupon amount.
 *
 * @author stefanny.rodriguez
 *
 */
@Getter
@AllArgsConstructor
public class RequestDTO {
    @NotEmpty(message = "The item list cannot be empty.")
    private List<String> item_ids;
    @Positive(message = "The coupon amount is not valid.")
    private Float amount;
}
