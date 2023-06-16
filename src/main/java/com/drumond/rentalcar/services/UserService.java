package com.drumond.rentalcar.services;

import com.drumond.rentalcar.dtos.UserDTO;
import com.drumond.rentalcar.enums.Role;
import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.mappers.UserMapper;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.UserRepository;
import com.drumond.rentalcar.utilities.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Contains all programmatic logic regarding the user.
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
    /**
     * Contains methods that allows to switch between {@link User} and {@link UserDTO}.
     */
    @Autowired
    private UserMapper userMapper;

    /**<ol>
     *  <li>Search for the user in database</li>
     *  <li>Checks is the {@link Optional<User>} has an {@link User} inside</li>
     *  <li>Sets the user token</li>
     *  <li>Updates the user in database</li>
     * </ol>
     * @param code the username
     * @param password the user password
     * @return the signed {@link User}
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

    /** Signs out a user from the system.
     * <ol>
     *     <li>Gets the user by their token</li>
     *     <li>Sets their token as null</li>
     * </ol>
     * @param token signed useridentifier key
     * @return The unsigned {@link User}
     */
    public User signOut(UUID token) {
        User user = getByToken(token);
        user.setToken(null);

        return userRepository.save(user);
    }

    /**
     * Gets the {@link User} that owns the given token.
     * @param token user identifier key
     * @return The {@link User} that owns the given token
     * @throws RentalCarException with HTTP response status <strong>403 (FORBIDDEN)</strong>
     */
    public User getByToken(UUID token) {
        return userRepository.findByToken(token).orElseThrow(() -> new RentalCarException(HttpStatus.FORBIDDEN,
                Constants.HEADER_FORBIDDEN, "You cannot perform the action with the provided token: " + token));
    }


    /**
     * Creates a new user into the system.
     * <ol>
     *     <li>Gets the user who will create another one</li>
     *     <li>Checks if the signed user is an employee</li>
     *     <li>Sets a new code for the new user</li>
     * </ol>
     * @param token signed user identifier key
     * @param newUser the user to create
     * @return The created {@link User}
     */
    public User create(UUID token, User newUser) {
        User signedUser = getByToken(token);

        if (signedUser.getRole().equals(Role.EMPLOYEE)) {
            newUser.setRole(Role.CLIENT);
            newUser.setPassword(null);
        }

        newUser.setCode(generateCode(newUser.getRole() != null ? newUser.getRole() : Role.CLIENT));

        return userRepository.save(newUser);
    }

    /**
     * <p>Generates a new user code.</p>
     * <p>Checks the amount of user that contains that role and adds a unity to it.</p>
     * @param role distinguish what are privileges that a user have in the system
     * @return the generated code accornding to the user role
     */
    private String generateCode(Role role) {
        DecimalFormat decimalFormat = new DecimalFormat("000");
        return switch (role) {
            case CLIENT -> "CLI_" + (decimalFormat.format(userRepository.countByRole(Role.CLIENT) + 1));
            case EMPLOYEE -> "EMP_" + (decimalFormat.format(userRepository.countByRole(Role.EMPLOYEE) + 1));
            case MANAGER -> "MAN_" + (decimalFormat.format(userRepository.countByRole(Role.MANAGER) + 1));
        };
    }

    /**
     * Gets all users existent in database.
     * @return The {@link List} of all {@link User}s
     */
    public List<User> getAll(UUID token) {
        getByToken(token);
        return userRepository.findAll();
    }

    /**
     * Gets users that have the provided code or name.
     * @param token signed user identifier key (who will perform the search)
     * @param code the username of the user to be found
     * @param name the name of the user to be found
     * @return the {@link User} {@link List} that have the provided code or name
     */
    public List<User> getByCodeOrName(UUID token, String code, String name) {
        getByToken(token);
        return userRepository.findByCodeOrName(code, name);
    }

    /**
     * Gets the user that have the provided id.
     * @param token signed user identifier key (who will perform the search)
     * @param id user identification number in database (who should be found)
     * @return The {@link User} that have the provided id
     * @throws RentalCarException if the provided id is not found in database
     */
    public User getById(UUID token, Long id) {
        if (token != null) {
            getByToken(token);
        }
        return userRepository.findById(id).orElseThrow(() -> new RentalCarException(HttpStatus.NOT_FOUND, Constants.HEADER_USER_NOT_FOUND, "The provided id: " + id + " does not exists in database."));
    }

    /**
     * Updates the name for the provided user.
     * <ol>
     *     <li>Gets the signed user</li>
     *     <li>Gets the user who will suffer the changes</li>
     *     <li>Checks if the user is an employee and is trying to update a client</li>
     *     <li>Performs the update in database</li>
     * </ol>
     * @param token signed user identifier key (who will perform the update)
     * @param body user data to update
     * @return the updated {@link User}
     * @throws RentalCarException if the signed user is a employee and is trying to update a user that is not a client
     */
    public User update(UUID token, UserDTO body) {
        User userWhoWillUpdate = getByToken(token);
        User userWhoWillBeUpdated = getById(null, body.getId());

        if (userWhoWillUpdate.getRole().equals(Role.EMPLOYEE) && !userWhoWillBeUpdated.getRole().equals(Role.CLIENT)) {
            throw new RentalCarException(HttpStatus.FORBIDDEN, "Action not allowed", "You do not have enough permitioms to perform this operation.");
        }

        userWhoWillBeUpdated = userMapper.partialUpdate(body, userWhoWillBeUpdated);

        return userWhoWillBeUpdated;
    }
}