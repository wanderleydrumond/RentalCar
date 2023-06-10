package com.drumond.rentalcar.services;

import com.drumond.rentalcar.exceptions.RentalCarException;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signIn(String code, String password) {
        Optional<User> userFound = userRepository.findByCodeAndPassword(code, password);

        if (userFound.isEmpty()) {
            throw new RentalCarException(HttpStatus.UNAUTHORIZED, "User not found in database", "Invalid credentials");
        }

        userFound.get().setToken(UUID.randomUUID());

        return userRepository.save(userFound.get());
    }
}
