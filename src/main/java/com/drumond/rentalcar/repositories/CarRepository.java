package com.drumond.rentalcar.repositories;

import com.drumond.rentalcar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface that makes the database communication layer role in relation with of the cars table.
 *
 * @author Wanderley Drumond
 */
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
}