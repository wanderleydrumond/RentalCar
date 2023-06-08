package com.drumond.rentalcar.models;

import com.drumond.rentalcar.enums.Segment;
import com.drumond.rentalcar.enums.converters.SegmentConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Convert(converter = SegmentConverter.class)
    private Segment segment;
    @NotNull
    @Column(name = "daily_price")
    private Double dailyPrice;
    @NotNull
    private String brand;
}