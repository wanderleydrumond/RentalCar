package com.drumond.rentalcar.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

/**
 * Rent information type that backend consumes and produces.
 *
 * @author Wanderley Drumond
 */
@Data
@Entity
@Table(name = "rents")
public class Rent {
    /**
     * Rent identification in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Who rent the car.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
    /**
     * The car to be rent.
     */
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    /**
     * When the car was rented.
     */
    @CreationTimestamp
    @Column(name = "rent_at")
    private ZonedDateTime rentAt;
    /**
     * When the car was returned.
     */
    @Column(name = "return_at")
    private ZonedDateTime returnAt;
}