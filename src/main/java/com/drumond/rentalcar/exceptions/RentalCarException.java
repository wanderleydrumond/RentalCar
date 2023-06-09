package com.drumond.rentalcar.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class RentalCarException extends RuntimeException {
    @Getter
    private HttpStatus httpStatus;

    public RentalCarException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}