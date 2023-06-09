package com.drumond.rentalcar.mappers;

import com.drumond.rentalcar.dtos.RentDTO;
import com.drumond.rentalcar.mappers.utils.RentMapperUtil;
import com.drumond.rentalcar.models.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * Responsible by transform {@link Rent} data that transits between backend and frontend.
 * @author Wanderley Drumond
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {RentMapperUtil.class})
public interface RentMapper {
    /**
     * Changes a {@link RentDTO} object into a {@link Rent} object.
     * @param rentDTO the object that will be transformed into Entity object
     * @return the {@link Rent} resultant object
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "returnAt", ignore = true)
    @Mapping(target = "client", source = "userId", qualifiedByName = "findUser")
    @Mapping(target = "car", source = "carDTO.id", qualifiedByName = "findCar")
    Rent toModel(RentDTO rentDTO);

    /**
     * Changes a {@link Rent} object into a {@link RentDTO} object.
     * @param rent the object that will be transformed into DTO object
     * @return the {@link RentDTO} resultant object
     */
    @Mapping(target = "userId", expression = "java((rent.getClient().getId()))")
    @Mapping(target = "carDTO", source = "car", qualifiedByName = "fromCartoCarDTO")
    RentDTO toDto(Rent rent);

    /**
     * Changes a {@link Rent} {@link List} into a {@link RentDTO} {@link List}.
     * @param rents the list that will be transformed into DTO list
     * @return the {@link RentDTO} resultant {@link List}
     */
    List<RentDTO> toDtos(List<Rent> rents);
}