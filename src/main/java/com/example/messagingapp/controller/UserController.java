package com.example.messagingapp.controller;

import com.example.messagingapp.entity.UserEntity;
import com.example.messagingapp.model.UserModel;
import com.example.messagingapp.repository.UserRepository;
import com.example.messagingapp.service.UserService;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

     @Autowired
     private UserService userService;

    @PostMapping
    public ResponseEntity register(@RequestBody UserEntity user) {
        try {
            UserModel userModel = userService.register(user);
            return ResponseEntity.ok(userModel);
        }
        catch(UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("an error occurred during new user registration");
        }
    }

    @GetMapping
    public ResponseEntity getById(@RequestParam(value = "id", defaultValue = "0") Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        }
        catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteById(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("error occurred");
        }
    }
}
