package com.drumond.rentalcar.mappers;

import com.drumond.rentalcar.dtos.UserDTO;
import com.drumond.rentalcar.models.User;
import jakarta.validation.Valid;
import org.mapstruct.*;

/**
 * Responsible by transform {@link User} data that transits between backend and frontend.
 * @author Wanderley Drumond
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    /**
     * Changes a {@link UserDTO} object into a {@link User} object.
     * @param userDTO the object that will be transformed into Entity object
     * @return the {@link User} resultant object
     */
    @Mapping(target = "id", ignore = true)
    User toModel(@Valid UserDTO userDTO);

    /**
     * Changes a {@link User} object into a {@link UserDTO} object.
     * @param user the object that will be transformed into DTO object
     * @return the {@link UserDTO} resultant object
     */
    UserDTO toDto(User user);

    /**
     * Updates a {@link User}.
     * @param userDTO object that contains the information to update
     * @param user object to be updated
     * @return The {@link User} updated
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "code", ignore = true)
    User partialUpdate(UserDTO userDTO, @MappingTarget User user);
}