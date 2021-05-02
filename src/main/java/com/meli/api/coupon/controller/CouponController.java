package com.meli.api.coupon.controller;

import com.meli.api.coupon.dto.RequestDTO;
import com.meli.api.coupon.dto.ResponseDTO;
import com.meli.api.coupon.exception.APIServiceException;
import com.meli.api.coupon.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for handling coupon requests and its suggested item list to purchase
 */
@RestController
@CrossOrigin
@RequestMapping("/coupon/")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService){
        this.couponService = couponService;
    }

    /**
     * This method takes a POST request to pass it to the correct service layer in order
     * to get the suggested item list to purchase and the total purchase
     *
     * @param requestDTO the user request, which is composed of the list of items to check and the coupon amount.
     * @return {@link ResponseDTO} the API response, which is composed of the suggested list of items to purchase
     *                              and the total purchase
     * @throws APIServiceException When something was wrong during the process or the coupon amount is insufficient.
     *
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> getSuggestedItems(@Valid @RequestBody RequestDTO requestDTO) throws APIServiceException {
        ResponseDTO responseDTO = couponService.getSuggestedItems(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
