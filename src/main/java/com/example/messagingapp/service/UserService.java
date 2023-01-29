package com.example.messagingapp.service;

import com.example.messagingapp.entity.UserEntity;
import com.example.messagingapp.model.UserModel;
import com.example.messagingapp.repository.UserRepository;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel register(UserEntity user) throws UserAlreadyExistsException {

        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException(String.format("user with email: %S already exists", user.getEmail()));
        }

        userRepository.save(user);

        return UserModel.toUserModel(user);
    }

    public UserModel findById(Long id) throws UserNotFoundException {

        UserEntity user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new UserNotFoundException(String.format("user with id: %d not found", id));
        }

        return UserModel.toUserModel(user);
    }

    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
