package com.drumond.rentalcar.mappers;

import com.drumond.rentalcar.dtos.CarDTO;
import com.drumond.rentalcar.models.Car;
import org.mapstruct.*;
/**
 * Responsible by transform {@link Car} data that transits between backend and frontend.
 * @author Wanderley Drumond
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CarMapper {
    /**
     * Changes a {@link CarDTO} object into a {@link Car} object.
     * @param carDTO the object that will be transformed into Entity object
     * @return the {@link Car} resultant object
     */
    @Mapping(target = "id", ignore = true)
    Car toModel(CarDTO carDTO);

    /**
     * Changes a {@link Car} object into a {@link CarDTO} object.
     * @param car the object that will be transformed into DTO object
     * @return the {@link CarDTO} resultant object
     */
    CarDTO toDto(Car car);

    /**
     * Updates a {@link Car}.
     * @param carDTO object that contains the information to update
     * @param car object to be updated
     * @return The {@link Car} updated
     */
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarDTO carDTO, @MappingTarget Car car);
}