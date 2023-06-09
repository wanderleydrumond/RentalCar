package com.drumond.rentalcar.mappers;

import com.drumond.rentalcar.dtos.CarDTO;
import com.drumond.rentalcar.models.Car;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CarMapper {
    @Mapping(target = "id", ignore = true)
    Car toModel(CarDTO carDTO);

    CarDTO toDto(Car car);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarDTO carDTO, @MappingTarget Car car);
}