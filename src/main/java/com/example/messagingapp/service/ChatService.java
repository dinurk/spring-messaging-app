package com.example.messagingapp.service;

import com.example.messagingapp.entity.ChatEntity;
import com.example.messagingapp.entity.UserEntity;
import com.example.messagingapp.dto.CreateChatDTO;
import com.example.messagingapp.repository.ChatRepository;
import com.example.messagingapp.repository.UserRepository;
import com.example.messagingapp.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;


    public ChatEntity create(CreateChatDTO createChatDTO) throws UserNotFoundException {

        UserEntity administrator = userRepository.findById(createChatDTO.getAdministratorId()).orElse(null);

        if(administrator == null) {
            throw new UserNotFoundException(String.format("user with id %d not found, request will be ignored", createChatDTO.getAdministratorId()));
        }

        ChatEntity chatEntity = new ModelMapper().map(createChatDTO, ChatEntity.class);
        chatEntity.setAdministrator(administrator);
        chatRepository.save(chatEntity);

        return chatEntity;
    }
}
