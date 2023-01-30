package com.example.messagingapp.service;

import com.example.messagingapp.dto.FindUserDTO;
import com.example.messagingapp.dto.RegisterUserDTO;
import com.example.messagingapp.entity.UserEntity;
import com.example.messagingapp.repository.UserRepository;
import com.example.messagingapp.exception.UserAlreadyExistsException;
import com.example.messagingapp.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity register(RegisterUserDTO registerUserDTO) throws UserAlreadyExistsException {

        if(userRepository.findByEmail(registerUserDTO.getEmail()) != null) {
            throw new UserAlreadyExistsException(String.format("user with email: %S already exists", registerUserDTO.getEmail()));
        }

        UserEntity userEntity = new ModelMapper().map(registerUserDTO, UserEntity.class);
        userRepository.save(userEntity);

        return userEntity;
    }

    public FindUserDTO findById(Long id) throws UserNotFoundException {

        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity == null) {
            throw new UserNotFoundException(String.format("user with id: %d not found", id));
        }

        return new ModelMapper().map(userEntity, FindUserDTO.class);
    }

    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
