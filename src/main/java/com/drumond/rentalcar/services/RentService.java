package com.drumond.rentalcar.services;

import com.drumond.rentalcar.models.Rent;
import com.drumond.rentalcar.repositories.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
}