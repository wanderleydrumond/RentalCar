package com.drumond.rentalcar.mappers.utils;

import com.drumond.rentalcar.dtos.CarDTO;
import com.drumond.rentalcar.mappers.CarMapper;
import com.drumond.rentalcar.models.Car;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.CarRepository;
import com.drumond.rentalcar.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentMapperUtil {

//    NOTE I could use the anotation @Autowired but is not recommended https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    /*@Named(value = "getCurrentDate")
    public ZonedDateTime getTime() {
        return ZonedDateTime.now();
    }*/

    @Named("findUser")
    public User findUserById(Long id) {
//        TODO change the exception from EntityNotFound to a custom one
        return this.userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Named("findCar")
    public Car findCarById(Long id) {
//        TODO change the exception from EntityNotFound to a custom one
        return this.carRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Named("fromCartoCarDTO")
    public CarDTO toCarDTO(Car car) {
        return carMapper.toDto(car);
    }
}
