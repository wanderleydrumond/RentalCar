package com.drumond.rentalcar.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * System custom exception that handles expected bad situations inside the whole system.
 * @author Wanderley Drumond
 */
public class RentalCarException extends RuntimeException {
    @Getter
    private HttpStatus httpStatus;
    @Getter
    private String header;

    public RentalCarException(String body) {
        super(body);
    }
    public RentalCarException(HttpStatus httpStatus, String body) {
        super(body);
        this.httpStatus = httpStatus;
    }
    public RentalCarException(HttpStatus httpStatus, String header, String body) {
        super(body);
        this.httpStatus = httpStatus;
        this.header = header;
    }
}