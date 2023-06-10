package com.drumond.rentalcar.services;

import com.drumond.rentalcar.enums.Role;
import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.UUID;

/**
 * Class that contains all the programmatic logic regarding the user.
 * @author Wanderley Drumond
 */
@Service
@AllArgsConstructor
public class UserService {

    /**
     * Contains all methods to manipulates database regarding users table.
     */
    @Autowired
    private UserRepository userRepository;

    /**<ol>
     *  <li>Search for the user in database</li>
     *  <li>Checks is the {@link Optional<User>} has an {@link User} inside</li>
     *  <li>Sets the user token</li>
     *  <li>Updates the user in database</li>
     * </ol>
     * @param code the username
     * @param password the user password
     * @return the logged {@link User}
     * @throws RentalCarException with response code 401 (UNAUTHORIZED) if username and/or password are not found in database.
     */
    public User signIn(String code, String password) {
        Optional<User> userFound = userRepository.findByCodeAndPassword(code, password);

        if (userFound.isEmpty()) {
            throw new RentalCarException(HttpStatus.UNAUTHORIZED, "User not found in database", "Invalid credentials");
        }

        userFound.get().setToken(UUID.randomUUID());

        return userRepository.save(userFound.get());
    }

    public User signOut(UUID token) {
        User user = findByToken(token);
        user.setToken(null);

        return userRepository.save(user);
    }

    public User findByToken(UUID token) {
        return userRepository.findByToken(token).orElseThrow(() -> new RentalCarException(HttpStatus.NOT_FOUND,
                "User not Found", "Does not exists any user with the provided token: " + token));
    }

    public User create(UUID token, User newUser) {
        User loggedUser = findByToken(token);

        if (loggedUser.getRole().equals(Role.EMPLOYEE)) {
            newUser.setRole(Role.CLIENT);
        }

        newUser.setCode(generateCode(newUser.getRole() != null ? newUser.getRole() : Role.CLIENT));

        return userRepository.save(newUser);
    }

    private String generateCode(Role role) {
        DecimalFormat decimalFormat = new DecimalFormat("000");
        return switch (role) {
            case CLIENT -> "CLI_" + (decimalFormat.format(userRepository.countByRole(Role.CLIENT) + 1));
            case EMPLOYEE -> "EMP_" + (decimalFormat.format(userRepository.countByRole(Role.EMPLOYEE) + 1));
            case MANAGER -> "MAN_" + (decimalFormat.format(userRepository.countByRole(Role.MANAGER) + 1));
        };
    }
}
