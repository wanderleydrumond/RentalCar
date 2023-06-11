package com.drumond.rentalcar.controllers;

import com.drumond.rentalcar.dtos.UserDTO;
import com.drumond.rentalcar.mappers.UserMapper;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.drumond.rentalcar.utilities.Constants.TOKEN;

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

    @PostMapping(value = "create/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<UserDTO> create(@PathVariable(value = TOKEN) UUID token, @RequestBody @Valid UserDTO userDTO) {
        User newUser = userMapper.toModel(userDTO);
        User createdUser = userService.create(token, newUser);
        UserDTO createdUserDTO = userMapper.toDto(createdUser);

        return ResponseEntity.ok().body(createdUserDTO);
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
     * @return {@link ResponseEntity<UserDTO>} with status code:
     *  <ul>
     *      <li><strong>200 (OK)</strong> if the user was signed out successfully</li>
     *      <li><strong>404 (NOT FOUND)</strong> if the provided token was not found in database</li>
     *  </ul>
     */
    @PutMapping(value = "signout/{" + TOKEN + "}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<UserDTO> signOut(@PathVariable(value = TOKEN) UUID token) {
        User user = userService.signOut(token);
        UserDTO userDTO = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDTO);
    }
}
