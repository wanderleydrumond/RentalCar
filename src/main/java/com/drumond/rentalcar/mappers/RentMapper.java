package com.drumond.rentalcar.mappers;

import com.drumond.rentalcar.dtos.RentDTO;
import com.drumond.rentalcar.mappers.utils.RentMapperUtil;
import com.drumond.rentalcar.models.Rent;
import org.mapstruct.*;

import java.time.ZonedDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {RentMapperUtil.class})
public interface RentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "returnAt", ignore = true)
    @Mapping(target = "client", source = "userId", qualifiedByName = "findUser")
    @Mapping(target = "car", source = "carDTO.id", qualifiedByName = "findCar")
    Rent toModel(RentDTO rentDTO);

    @Mapping(target = "userId", expression = "java((rent.getClient().getId()))")
    @Mapping(target = "carDTO", source = "car", qualifiedByName = "fromCartoCarDTO")
    RentDTO toDto(Rent rent);

    @AfterMapping
    default void setRentAt(@MappingTarget Rent rent) {
        rent.setRentAt(ZonedDateTime.now());
    }
}