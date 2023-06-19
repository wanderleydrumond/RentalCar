package com.drumond.rentalcar.controllers;

import com.drumond.rentalcar.dtos.RentDTO;
import com.drumond.rentalcar.mappers.RentMapper;
import com.drumond.rentalcar.models.Rent;
import com.drumond.rentalcar.services.RentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.drumond.rentalcar.utilities.Constants.ID;
import static com.drumond.rentalcar.utilities.Constants.TOKEN;

/**
 * Contains all requisition methods that refers to rent.
 *
 * @author Wanderley Drumond
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("rent/")
public class RentController {
    /**
     * Contains methods that allows to switch between {@link Rent} and {@link RentDTO}.
     * @see <a href="https://www.baeldung.com/java-spring-field-injection-cons">Why Is Field Injection Not Recommended?</a>
     */
    @Autowired
    RentMapper rentMapper;
    /**
     * Contains all methods related to business layer that concerns to rent.
     * @see <a href="https://www.baeldung.com/java-spring-field-injection-cons">Why Is Field Injection Not Recommended?</a>
     */
    @Autowired
    RentService rentService;

    /**
     * Rent a car to a client.
     * @param token signed user identifier key (who will perform the operation)
     * @param body data to perform the new rent
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>201 (CREATED)</strong> if the rent was created, along with the {@link RentDTO}</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @PostMapping(value = "create/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<RentDTO> create(@PathVariable(value = TOKEN) UUID token, @RequestBody @Valid RentDTO body) {
        Rent newRent = rentMapper.toModel(body);
        Rent createdRent = rentService.create(token, newRent);
        RentDTO createdRentDTO = rentMapper.toDto(createdRent);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRentDTO);
    }

    /**
     * Return a car to the store. (Ends the provided rent)
     * @param token signed user identifier key (who will perform the operation)
     * @param rentId rent identfication number
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if user was updated, along with the {@link RentDTO}</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *      <li><strong>404 (NOT FOUND)</strong> if the provided rent id was not found in database</li>
     *  </ul>
     */
    @PutMapping(value = "update/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<RentDTO> update(@PathVariable(value = TOKEN) UUID token, @RequestParam(value = ID) Long rentId){
        Rent rentUpdated = rentService.update(token, rentId);
        RentDTO rentDTOUpdated = rentMapper.toDto(rentUpdated);

        return ResponseEntity.ok().body(rentDTOUpdated);
    }

    /**
     * Gets all cars rent by the provided client id.
     * @param token    signed user identifier key (who will perform the search)
     * @param clientId target user (who will have all rents searched)
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if user was updated, along with the {@link RentDTO} {@link List}</li>
     *      <li><strong>204 (NO CONTENT)</strong> if no rents were found in database</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @GetMapping(value = "by-user/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<List<RentDTO>> getAllCarsRentByClientId(@PathVariable(value = TOKEN) UUID token, @RequestParam(value = ID) Long clientId){
        List<Rent> rents = rentService.getAllCarsRentByClientId(token, clientId);
        List<RentDTO> rentsDTO = rentMapper.toDtos(rents);

        return rentsDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(rentsDTO);
    }
}