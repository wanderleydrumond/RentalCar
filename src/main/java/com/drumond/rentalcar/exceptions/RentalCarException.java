package com.drumond.rentalcar.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * System custom exception that handles expected bad situations inside the whole system.
 * @author Wanderley Drumond
 */
public class RentalCarException extends RuntimeException {
    /**
     * The exception code status of the request.
     */
    @Getter
    private HttpStatus httpStatus;
    /**
     * Message to be displayed in the custom exception header.
     */
    @Getter
    private String header;

    /**
     * Custom exception with only the body message.
     * @param body message to be displayed in the body
     */
    public RentalCarException(String body) {
        super(body);
    }

    /**
     * Custom exception with the HTTP code status and body message.
     * @param httpStatus the exception code status of the request
     * @param body message to be displayed in the body
     */
    public RentalCarException(HttpStatus httpStatus, String body) {
        this(body);
        this.httpStatus = httpStatus;
    }

//    COMMENT does chained constructor waste processing?
    /**
     * Custom exception with the HTTP code status, header and body message.
     * @param httpStatus the exception code status of the request
     * @param header message to be displayed in the header
     * @param body message to be displayed in the body
     */
    public RentalCarException(HttpStatus httpStatus, String header, String body) {
        this(httpStatus, body);
        this.header = header;
    }
}