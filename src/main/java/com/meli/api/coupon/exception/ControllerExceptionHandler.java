package com.meli.api.coupon.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * This class handles all the exception
 *
 * @author stefanny.rodriguez
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * This method handles the exceptions related to the API
     * @param ex Exception thrown
     * @param request User's request
     * @return {@link ErrorMessage} With all the error details
     */
    @ExceptionHandler(APIServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage apiServiceException(APIServiceException ex, WebRequest request) {
        LOG.error("API Service Exception: {}", ex.getMessage(), ex);
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * This method handles the exceptions related to the MercadoLibre API calls
     * @param ex Exception thrown
     * @param request User's request
     * @return {@link ErrorMessage} With all the error details
     */
    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class, ResourceAccessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage ExternalApiCallsExceptionHandler(Exception ex, WebRequest request) {
        LOG.error("Error calling MercadoLibre API", ex);
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * This method handles the global exception
     * @param ex Exception thrown
     * @param request User's request
     * @return {@link ErrorMessage} With all the error details
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        LOG.error("An unexpected error occurred", ex);
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
