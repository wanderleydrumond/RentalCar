package com.drumond.rentalcar.dtos;

import com.drumond.rentalcar.enums.Segment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Car information type that the frontend consumes and produces.
 *
 * @author Wanderley Drumond
 */
@NoArgsConstructor
@Getter
@Setter
public class CarDTO {
    private Long id;
    private Segment segment;
    private BigDecimal dailyPrice;
    @NotBlank(message = "Brand is mandatory")
    private String brand;
}