package com.drumond.rentalcar.controllers;

import com.drumond.rentalcar.dtos.UserDTO;
import com.drumond.rentalcar.mappers.UserMapper;
import com.drumond.rentalcar.models.User;
import com.drumond.rentalcar.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Contains all requisition methods that refers to user.
 *
 * @author Wanderley Drumond
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("user/")
public class UserController {
    private final String ID = "id";

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PutMapping(value = "signin")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<UserDTO> signIn(@RequestParam(value = "code") String code, @RequestParam(value = "password") String password) {
        User user = this.userService.signIn(code, password);
        UserDTO userDTO = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDTO);
    }
}
