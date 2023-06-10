package com.drumond.rentalcar.models;

import com.drumond.rentalcar.enums.Segment;
import com.drumond.rentalcar.enums.converters.SegmentConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotNull
    @Column(name = "daily_price")
    private Double dailyPrice;
    /**
     * The brand which the car belongs.
     */
    @NotNull
    private String brand;
}