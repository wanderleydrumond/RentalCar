package com.drumond.rentalcar.repositories;

import com.drumond.rentalcar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Interface that makes the database communication layer role in relation with of the users table.
 *
 * @author Wanderley Drumond
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Boolean existsByCodeAndName(String code, String name);
    Optional<User> findByCodeAndPassword(String code, String password);
}