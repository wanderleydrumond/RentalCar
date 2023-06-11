package com.drumond.rentalcar.mappers.utils;

import com.drumond.rentalcar.dtos.CarDTO;
import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.mappers.CarMapper;
import com.drumond.rentalcar.models.Car;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.CarRepository;
import com.drumond.rentalcar.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 * Contains utilities methods to aid the mapping.
 */
@Component
@RequiredArgsConstructor
public class RentMapperUtil {

//    NOTE I could use the anotation @Autowired but is not recommended https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it
    /**
     * Contains all methods to manipulates database regarding users table.
     */
    private final UserRepository userRepository;
    /**
     * Contains all methods to manipulates database regarding cars table.
     */
    private final CarRepository carRepository;
    /**
     * Contains method that allows to switch between {@link Car} and {@link CarDTO}.
     */
    private final CarMapper carMapper;

    /**
     * Gets a user by their id.
     * @param id identification user number in database
     * @return the found {@link User}
     * @throws RentalCarException if the provided id doesn't match with any user in database
     */
    @Named("findUser")
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new RentalCarException("User with the provided id: " + id + "not found."));
    }

    /**
     * Gets a car by their id.
     * @param id identification car number in database
     * @return the found {@link Car}
     * @throws RentalCarException if the provided id doesn't match with any car in database
     */
    @Named("findCar")
    public Car getCarById(Long id) {
        return this.carRepository.findById(id).orElseThrow(() -> new RentalCarException("Car with the provided id: " + id + "not found."));
    }

    /**
     * Transforms the provided {@link Car} into {@link CarDTO}
     * <p><em>{@linkplain com.drumond.rentalcar.mappers.RentMapper#toDto toDTO} auxiliary method</em></p>
     * @param car object to be transformed
     * @return the correspondent {@link CarDTO}
     */
    @Named("fromCartoCarDTO")
    public CarDTO toCarDTO(Car car) {
        return carMapper.toDto(car);
    }
}
