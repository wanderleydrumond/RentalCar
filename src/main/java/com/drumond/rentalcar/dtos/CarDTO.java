package com.drumond.rentalcar.dtos;

import com.drumond.rentalcar.enums.Segment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotBlank(message = "Segment is mandatory")
    private Segment segment;
    @NotNull
    private Double dailyPrice;
    @NotBlank(message = "Brand is mandatory")
    private String brand;
}