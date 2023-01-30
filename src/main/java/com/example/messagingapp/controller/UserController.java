package com.example.messagingapp.controller;

import com.example.messagingapp.dto.RegisterUserDTO;
import com.example.messagingapp.entity.UserEntity;
import com.example.messagingapp.service.UserService;
import com.example.messagingapp.exception.UserAlreadyExistsException;
import com.example.messagingapp.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid RegisterUserDTO registerUserDTO) {
        try {
            UserEntity userEntity = userService.register(registerUserDTO);

            return ResponseEntity.created(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(userEntity.getId())
                            .toUri())
                    .body(registerUserDTO);
        }
        catch(UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        }
        catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.status(204).body(null);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("error occurred");
        }
    }
}
