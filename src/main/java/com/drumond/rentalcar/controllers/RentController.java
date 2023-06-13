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

import java.util.UUID;

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
     */
    @Autowired
    RentMapper rentMapper;
    /**
     * Contains all methods related to business layer that concerns to rent.
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
}