package com.drumond.rentalcar.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
public class RentDTO {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private CarDTO carDTO;
    private ZonedDateTime rentAt;
    private ZonedDateTime returnAt;
}