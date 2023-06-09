package com.drumond.rentalcar.dtos;

import com.drumond.rentalcar.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private UUID token;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String code;
    private Role role;
}