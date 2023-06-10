package com.drumond.rentalcar.mappers;

import com.drumond.rentalcar.dtos.UserDTO;
import com.drumond.rentalcar.models.User;
import jakarta.validation.Valid;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toModel(@Valid UserDTO userDTO);

    UserDTO toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "code", ignore = true)
    User partialUpdate(UserDTO userDTO, @MappingTarget User user);
}