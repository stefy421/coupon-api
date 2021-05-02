package com.meli.api.coupon.exception;

/**
 * Custom Exception class to handle the API exceptions
 *
 * @author stefanny.rodriguez
 *
 */

public class APIServiceException extends RuntimeException{

    public APIServiceException(String msg){
        super(msg);
    }
}
