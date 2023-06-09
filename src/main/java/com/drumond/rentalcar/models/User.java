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
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private UUID token;
    @NotNull
    private String name;
    @NotNull
    private String code;
    @Convert(converter = RoleConverter.class)
    private Role role;
}