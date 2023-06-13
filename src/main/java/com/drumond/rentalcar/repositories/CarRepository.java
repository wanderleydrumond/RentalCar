package com.drumond.rentalcar.repositories;

import com.drumond.rentalcar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Interface that makes the database communication layer role in relation with of the cars table.
 *
 * @author Wanderley Drumond
 */
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    /**
     * Finds all cars that have the provided brand in database.
     * @param brand of the cars to be found
     * @return the {@link Car} {@link List} that have the provided brand
     */
    List<Car> findByBrand(String brand);
}