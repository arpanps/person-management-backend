package com.person.rest.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.person.execption.ApiError;
import com.person.execption.EntityNotFoundException;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@RestControllerAdvice
public class PersonControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(PersonControllerAdvice.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ApiError handlePackageNotFoundException(final EntityNotFoundException entityNotFoundException) {
        return new ApiError(entityNotFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ApiError handleIllegalArgumentException(final IllegalArgumentException illegalArgumentException) {
        return new ApiError(illegalArgumentException.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiError handleIllegalArgumentException(final Exception exception) {
        log.error("Unknown error ", exception);

        return new ApiError(exception.getMessage());
    }
}
