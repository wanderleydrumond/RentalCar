package com.drumond.rentalcar.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class that implements the proper behaviour every time that an expected exception like {@link RentalCarException} happens.
 * @see <a href="https://www.tutorialspoint.com/spring_boot/spring_boot_exception_handling.htm">Spring Boot - Exception Handling</a>
 * @author Wanderley Drumond
 */
@ControllerAdvice
public class RentalCarExceptionHandler {
    /**
     * Mounts the {@link ResponseEntity} that will have the proper exception message
     *
     * @param rentalCarException {@link Exception} that will handle the exception captured
     * @return {@link ResponseEntity} containing the HTTP status, header and body exception message
     */
    @ExceptionHandler(value = {RentalCarException.class})
    protected ResponseEntity<Object> toResponse(RentalCarException rentalCarException) {
        return ResponseEntity.status(rentalCarException.getHttpStatus())
                .header(rentalCarException.getMessage())
                .body(rentalCarException.getMessage());
    }
}