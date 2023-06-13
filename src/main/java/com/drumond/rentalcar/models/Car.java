package com.drumond.rentalcar.models;

import com.drumond.rentalcar.enums.Segment;
import com.drumond.rentalcar.enums.converters.SegmentConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Car information type that backend consumes and produces.
 *
 * @author Wanderley Drumond
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    /**
     * Car identification in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The {@link Segment} which the car belongs.
     */
    @Convert(converter = SegmentConverter.class)
    private Segment segment;
    /**
     * Daily cost of the car.
     */
    @Column(name = "daily_price")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "200.0")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal dailyPrice;
    /**
     * The brand which the car belongs.
     */
    @NotBlank(message = "Brand is mandatory")
    private String brand;
}