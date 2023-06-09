package com.drumond.rentalcar.models;

import com.drumond.rentalcar.enums.Role;
import com.drumond.rentalcar.enums.converters.RoleConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID token;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true, updatable = false)
    private String code;
    @Convert(converter = RoleConverter.class)
    private Role role;
}