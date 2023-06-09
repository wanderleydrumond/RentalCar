package com.drumond.rentalcar.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RentalCarExceptionHandler {
    @ExceptionHandler(value = {RentalCarException.class})
    protected ResponseEntity<Object> toResponse(RentalCarException rentalCarException) {
        return ResponseEntity.status(rentalCarException.getHttpStatus()).header(rentalCarException.getMessage()).body(rentalCarException.getMessage());
    }
}