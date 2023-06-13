package com.drumond.rentalcar.utilities;

import com.drumond.rentalcar.enums.Role;
import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Inserts some data in databse when the system is started.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoadDatabase {
    private final UserRepository userRepository;

    /**
     * Adds two new users in database.
     * @param applicationStartedEvent event that listens that triggers the action to be done
     * @throws RentalCarException if some error occured on the save process
     */
    @EventListener
    @Transactional(rollbackFor = Throwable.class)
    public void loadUsers(ApplicationStartedEvent applicationStartedEvent) {
    final String MANAGER_CODE = "MAN_001",
                 EMPLOYEE_CODE = "EMP_001",
                 CLIENT_CODE = "CLI_001",
                 MANAGER_NAME = "Migi",
                 EMPLOYEE_NAME = "Shinichi",
                 CLIENT_NAME = "Satomi",
                 PASSWORD = "123456",
                 LOG_ERROR = "Error loading database";


        try {
            if (!userRepository.existsByCodeAndName(MANAGER_CODE, MANAGER_NAME)){
                User manager = new User();
                manager.setCode(MANAGER_CODE);
                manager.setName(MANAGER_NAME);
                manager.setPassword(PASSWORD);
                manager.setRole(Role.MANAGER);

                userRepository.save(manager);
            }

            if (!userRepository.existsByCodeAndName(EMPLOYEE_CODE, EMPLOYEE_NAME)){
                User employee = new User();
                employee.setCode(EMPLOYEE_CODE);
                employee.setName(EMPLOYEE_NAME);
                employee.setPassword(PASSWORD);
                employee.setRole(Role.EMPLOYEE);

                userRepository.save(employee);
            }

            if (!userRepository.existsByCodeAndName(CLIENT_CODE, CLIENT_NAME)){
                User client = new User();
                client.setCode(CLIENT_CODE);
                client.setName(CLIENT_NAME);
                client.setPassword(PASSWORD);
                client.setRole(Role.CLIENT);

                userRepository.save(client);
            }
        } catch (Exception exception) {
            log.error(LOG_ERROR, exception);
            throw new RentalCarException(LOG_ERROR);
        }
    }
}
