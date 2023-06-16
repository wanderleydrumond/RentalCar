package com.drumond.rentalcar.repositories;

import com.drumond.rentalcar.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface that makes the database communication layer role in relation with of the rents table.
 *
 * @author Wanderley Drumond
 */
public interface RentRepository extends JpaRepository<Rent, Long>, JpaSpecificationExecutor<Rent> {
    /**
     * Finds in database all rents that have the car id of the provided user id.
     * @param userId target user (who will have all rents searched)
     * @return the {@link Rent} {@link List} from the provided user id
     */
    @Query(value = "SELECT * FROM rents WHERE user_id =:userId GROUP BY car_id", nativeQuery = true)
    List<Rent> findAllByClientId(@Param("userId") Long userId);
}