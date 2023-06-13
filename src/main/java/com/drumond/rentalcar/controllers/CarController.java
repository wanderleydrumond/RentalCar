package com.drumond.rentalcar.controllers;

import com.drumond.rentalcar.dtos.CarDTO;
import com.drumond.rentalcar.enums.Segment;
import com.drumond.rentalcar.mappers.CarMapper;
import com.drumond.rentalcar.models.Car;
import com.drumond.rentalcar.services.CarService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.drumond.rentalcar.utilities.Constants.*;

/**
 * Contains all requisition methods that refers to car.
 *
 * @author Wanderley Drumond
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("car/")
public class CarController {
    /**
     * Contains methods that allows to switch between {@link Car} and {@link CarDTO}.
     */
    @Autowired
    CarMapper carMapper;
    /**
     * Contains all methods related to business layer that concerns to car.
     */
    @Autowired
    CarService carService;

    /**
     * Creates a new car.
     * @param token signed user identifier key (who will create a new car)
     * @param body data to create a new car
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>201 (CREATED)</strong> if the car was created, along with the {@link CarDTO}</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @PostMapping(value = "create/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<CarDTO> create(@PathVariable(value = TOKEN) UUID token, @RequestBody @Valid CarDTO body) {
        Car newCar = carMapper.toModel(body);
        Car createdCar = carService.create(token, newCar);
        CarDTO createdCarDTO = carMapper.toDto(createdCar);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarDTO);
    }

    /**
     * Gets cars that have the provided brand.
     * @param token signed user identifier key (who will perform the search)
     * @param brand of the car to be found
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if, at least one car was found, along with the {@link CarDTO} {@link List}</li>
     *      <li><strong>204 (NO CONTENT)</strong> if no cars were found in database</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @GetMapping(value = "by-brand/{" + TOKEN + "}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<CarDTO>> getAllByBrand(@PathVariable(value = TOKEN) UUID token, @RequestParam(value = BRAND) String brand) {
        List<Car> carsFound = carService.getByBrand(token, brand);
        List<CarDTO> carsDTOFound = carMapper.toDtos(carsFound);

        return carsDTOFound.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(carsDTOFound);
    }

    /**
     * Gets cars that have the provided segment.
     * @param token signed user identifier key (who will perform the search)
     * @param segment of the car to be found
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if, at least one car was found, along with the {@link CarDTO} {@link List}</li>
     *      <li><strong>204 (NO CONTENT)</strong> if no cars were found in database</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @GetMapping(value = "by-segment/{" + TOKEN + "}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<CarDTO>> getAllBySegment(@PathVariable(value = TOKEN) UUID token, @RequestParam(value = SEGMENT) Segment segment) {
        List<Car> carsFound = carService.getBySegment(token, segment);
        List<CarDTO> carsDTOFound = carMapper.toDtos(carsFound);

        return carsDTOFound.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(carsDTOFound);
    }
}