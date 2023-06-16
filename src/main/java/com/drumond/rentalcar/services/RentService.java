package com.drumond.rentalcar.services;

import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.models.Rent;
import com.drumond.rentalcar.repositories.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Contains all programmatic logic regarding rent.
 * @author Wanderley Drumond
 */
@Service
@AllArgsConstructor
public class RentService {
    @Autowired
    UserService userService;
    @Autowired
    RentRepository rentRepository;

    /**
     * Rent a car to a client.
     * <ol>
     *     <li>Checks the token of the signed user. (employee and manager are allowed)</li>
     *     <li>Creates a new {@link Rent} in database</li>
     * </ol>
     * @param token signed user identifier key (who will perform the operation)
     * @param newRent object to be created
     * @return the new created {@link Rent}
     */
    public Rent create(UUID token, Rent newRent) {
        userService.getByToken(token);

        return rentRepository.save(newRent);
    }

    /**
     * Updates the rent return date.
     * <ol>
     *     <li>Gets the rent to be updated</li>
     *     <li>Sets the return date with the provided data</li>
     * </ol>
     * @param token signed user identifier key (who will perform the operation)
     * @param rentId rent identfication number
     * @return the updated {@link Rent}
     */
    public Rent update(UUID token, Long rentId) {
        Rent finalizedRent = getById(token, rentId);
        finalizedRent.setReturnAt(ZonedDateTime.now());

        return rentRepository.save(finalizedRent);
    }

    /**
     * Gets a rent by its id.
     * <ol>
     *     <li>Checks if user has a valid token</li>
     *     <li>Finds the rent that have the provided id</li>
     *     <li>Checks if the {@link Optional} has the proper {@link Rent} inside</li>
     * </ol>
     * <em>{@linkplain RentService#update update} auxiliary method</em>
     * @param token signed user identifier key (who will perform the search)
     * @param id rent identification number
     * @return the {@link Rent} that have the provided id
     * @throws RentalCarException if the provided rent id was not found in database
     */
    private Rent getById(UUID token, Long id) {
        if (token != null) {
            userService.getByToken(token);
        }

        Optional<Rent> optionalRent = rentRepository.findById(id);

        if (optionalRent.isEmpty()) {
            throw new RentalCarException(HttpStatus.NOT_FOUND, "Rent not found", "The rent with the provided id: " + id + " was not found in database.");
        }

        return optionalRent.get();
    }

    /**
     * Gets all cars rent by the provided client id.
     * <ol>
     *     <li>Checks if user is signed in (employee and manager are allowed)</li>
     * </ol>
     * @param token    signed user identifier key (who will perform the search)
     * @param clientId target user (who will have all rents searched)
     * @return the {@link Rent} {@link List} from the provided user id
     */
    public List<Rent> getAllCarsRentByClientId(UUID token, Long clientId) {
        userService.getByToken(token);

        return rentRepository.findAllByClientId(clientId);
    }
}