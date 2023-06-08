package com.drumond.rentalcar.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @CreationTimestamp
    @Column(name = "rent_at")
    private ZonedDateTime rentAt;
    @Column(name = "return_at")
    private ZonedDateTime returnAt;
}