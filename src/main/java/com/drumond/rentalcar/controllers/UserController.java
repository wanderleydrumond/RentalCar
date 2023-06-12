package com.drumond.rentalcar.controllers;

import com.drumond.rentalcar.dtos.UserDTO;
import com.drumond.rentalcar.mappers.UserMapper;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.drumond.rentalcar.utilities.Constants.*;

/**
 * Contains all requisition methods that refers to user.
 * @author Wanderley Drumond
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("user/")
public class UserController {

    /**
     * Contains all methods related to business layer that concerns to user.
     */
    @Autowired
    private UserService userService;
    /**
     * Contains methods that allows to switch between {@link User} and {@link UserDTO}.
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * Creates a new user in the system.
     * @param token signed user identifier key (who will create the new user)
     * @param userDTO Necessary data that make up a user
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>201 (CREATED)</strong> if the user was created, along with the {@link User}</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @PostMapping(value = "create/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<UserDTO> create(@PathVariable(value = TOKEN) UUID token, @RequestBody @Valid UserDTO userDTO) {
        User newUser = userMapper.toModel(userDTO);
        User createdUser = userService.create(token, newUser);
        UserDTO createdUserDTO = userMapper.toDto(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    /**
     * Signs a user into the system.
     * @param code the username
     * @param password the user password
     * @return {@link ResponseEntity<UserDTO>} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if the user was signed in successfully</li>
     *      <li><strong>401 (UNAUTHORISED)</strong> if the code or/and password is incorrect</li>
     *  </ul>
     */
    @PutMapping(value = "signin")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<UserDTO> signIn(@RequestParam(value = "code") String code, @RequestParam(value = "password") String password) {
        User user = userService.signIn(code, password);
        UserDTO userDTO = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDTO);
    }

    /**
     * Signs out the signed user from the system.
     * @param token signed user identifier key
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if the user was signed out successfully</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the current user is already signed out</li>
     *  </ul>
     */
    @PutMapping(value = "signout/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<UserDTO> signOut(@PathVariable(value = TOKEN) UUID token) {
        User user = userService.signOut(token);
        UserDTO userDTO = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDTO);
    }

    /**
     * Gets all users in the system.
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if, at least one user was found, along with the {@link User} {@link List}</li>
     *      <li><strong>204 (NO CONTENT)</strong> if no users were found in database</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @GetMapping(value = "all/{" + TOKEN + "}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTO>> getAll(@PathVariable(value = TOKEN) UUID token){
        List<User> users = userService.getAll(token);
        List<UserDTO> usersDTO = userMapper.toDTOs(users);

        return usersDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(usersDTO);
    }

    /**
     * Gets users that have the provided code or name.
     * @param token signed user identifier key (who will perform the search)
     * @param code the username of the user to be found
     * @param name the name of the user to be found
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if, at least one user was found, along with the {@link User} {@link List}</li>
     *      <li><strong>204 (NO CONTENT)</strong> if no users were found in database</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @GetMapping(value = "by-code-name/{" + TOKEN + "}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTO>> getByCodeOrName(@PathVariable(value = TOKEN) UUID token, @RequestParam(value = CODE) String code,@RequestParam(value = NAME) String name){
        List<User> users = userService.getByCodeOrName(token, code, name);
        List<UserDTO> usersDTO = userMapper.toDTOs(users);

        return usersDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(usersDTO);
    }

    /**
     * Gets the user that have the provided id.
     * @param token signed user identifier key (who will perform the search)
     * @param id user identification number in database (who should be found)
     * @return {@link ResponseEntity} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if user was found, along with the {@link User}</li>
     *      <li><strong>204 (NO CONTENT)</strong> if no users were found in database</li>
     *      <li><strong>403 (FORBIDDEN)</strong> if the provided token was not found in database</li>
     *      <li><strong>404 (NOT FOUND)</strong> if the provided id was not found in database</li>
     *  </ul>
     */
    @GetMapping(value = "by-id/{" + TOKEN + "}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserDTO> getById(@PathVariable(value = TOKEN) UUID token, @RequestParam(value = ID) Integer id) {
        User user = userService.getById(token, id);
        UserDTO userDTO = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDTO);
    }
}
