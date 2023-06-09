package com.drumond.rentalcar.models;

import com.drumond.rentalcar.enums.Segment;
import com.drumond.rentalcar.enums.converters.SegmentConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = SegmentConverter.class)
    private Segment segment;
    @NotNull
    @Column(name = "daily_price")
    private Double dailyPrice;
    @NotNull
    private String brand;
}