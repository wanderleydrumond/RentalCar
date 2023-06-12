package com.drumond.rentalcar.repositories;

import com.drumond.rentalcar.enums.Role;
import com.drumond.rentalcar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface that makes the database communication layer role in relation with of the users table.
 *
 * @author Wanderley Drumond
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * Checks if exists a user with the provided code and name.
     * @param code the username
     * @param name the name of the user
     * @return
     *  <ul>
     *      <li><strong>True</strong>, if found</li>
     *      <li><strong>False</strong>, if does not found</li>
     *  </ul>
     */
    Boolean existsByCodeAndName(String code, String name);

    /**
     * Finds in database a user that have the provided code and password.
     * @param code the username
     * @param password the user password
     * @return the {@link User} that owns the provided credentials
     */
    Optional<User> findByCodeAndPassword(String code, String password);

    /**
     * Finds in database a user that have the provided UUID code.
     * @param token the signed user identified key
     * @return the {@link User} that owns the provided token
     */
    Optional<User> findByToken(UUID token);

    /**
     * Counts the amount of users that have the provided role.
     * @param role the user {@link Role}
     * @return The amount of users that have the provided role.
     */
    Integer countByRole(Role role);

    /**
     * Finds in database the users that have the provided code or name.
     * @param code the username of the user to be found
     * @param name the name of the user to be found
     * @return the {@link User} {@link List} that have the provided code or name
     */
    List<User> findByCodeOrName(String code, String name);
}