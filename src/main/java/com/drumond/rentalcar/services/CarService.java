package com.drumond.rentalcar.services;

import com.drumond.rentalcar.enums.Role;
import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.models.Car;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarService {
    /**
     * Contains all methods related to business layer that concerns to user.
     */
    @Autowired
    private UserService userService;
    /**
     * Contains all methods to manipulates database regarding cars table.
     */
    @Autowired
    private CarRepository carRepository;

    /**
     * Creates a new car.
     * <ol>
     *     <li>Gets the user who will create a new car</li>
     *     <li>Checks if this user is a manager</li>
     *     <li>Saves the new car in database</li>
     * </ol>
     * @param token signed user identifier key (who will create a new car)
     * @param newCar data to create a new car
     * @return the new {@link Car} created
     */
    public Car create(UUID token, Car newCar) {
        User creator = userService.getByToken(token);

        if (!creator.getRole().equals(Role.MANAGER)) {
            throw new RentalCarException(HttpStatus.FORBIDDEN, "Action not allowed", "You do not have enough permitioms to perform this operation.");
        }

        return carRepository.save(newCar);
    }
}
