package com.drumond.rentalcar.utilities;

import com.drumond.rentalcar.enums.Role;
import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadDatabase {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @EventListener
    @Transactional(rollbackFor = Throwable.class)
    public void loadUsers(ApplicationStartedEvent applicationStartedEvent) {
    final String MANAGER_CODE = "MAN_001",
                 EMPLOYEE_CODE = "EMP_001",
                 MANAGER_NAME = "Migi",
                 EMPLOYEE_NAME = "Shinichi",
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
        } catch (Exception exception) {
            log.error(LOG_ERROR, exception);
            throw new RentalCarException(LOG_ERROR);
        }
    }
}
