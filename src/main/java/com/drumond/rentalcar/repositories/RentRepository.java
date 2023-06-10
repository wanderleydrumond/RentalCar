package com.drumond.rentalcar.repositories;

import com.drumond.rentalcar.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface that makes the database communication layer role in relation with of the rents table.
 *
 * @author Wanderley Drumond
 */
public interface RentRepository extends JpaRepository<Rent, Long>, JpaSpecificationExecutor<Rent> {
}