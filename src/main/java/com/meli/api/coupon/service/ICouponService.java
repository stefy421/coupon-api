package com.meli.api.coupon.service;

import com.meli.api.coupon.dto.RequestDTO;
import com.meli.api.coupon.dto.ResponseDTO;
import com.meli.api.coupon.exception.APIServiceException;

/**
 * Interface that exposes the methods for the coupons.
 *
 * @author stefanny.rodriguez
 *
 */
public interface ICouponService {

    /**
     * This method returns a suggested list of item to be purchased and the total purchase with the coupon amount.
     *
     * @param request the user request, which is composed of the list of items to check and the coupon amount.
     * @return {@link ResponseDTO} the API response, which is composed of the suggested list of items to purchase
     * and the total purchase
     * @throws APIServiceException When something was wrong during the process or the coupon amount is insufficient.
     */
    default ResponseDTO getSuggestedItems(RequestDTO request) {
        return null;
    }
}
