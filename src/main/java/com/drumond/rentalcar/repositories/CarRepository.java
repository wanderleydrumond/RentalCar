package com.drumond.rentalcar.repositories;

import com.drumond.rentalcar.enums.Segment;
import com.drumond.rentalcar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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

    /**
     * Finds all cars that have the provided segment in database.
     * @param segment of the cars to be found
     * @return the {@link Car} {@link List} that have the provided segment
     */
    List<Car> findBySegment(Segment segment);

    /**
     * Finds in database all cars that have, in rents table the return_at column at null.
     * @return The {@link Car} {@link List} that have, in rents table the return_at column at null
     */
    @Query(value = "SELECT * FROM cars WHERE id NOT IN (SELECT car_id FROM rents WHERE return_at IS NULL)", nativeQuery = true)
    List<Car> findAvailables();
}