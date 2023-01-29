package com.example.messagingapp.service;

import com.example.messagingapp.entity.ChatEntity;
import com.example.messagingapp.entity.UserEntity;
import com.example.messagingapp.model.ChatModel;
import com.example.messagingapp.repository.ChatRepository;
import com.example.messagingapp.repository.UserRepository;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    public ChatModel create(ChatModel chatModel) throws UserNotFoundException {

        UserEntity administrator = userRepository.findById(chatModel.getAdministratorId()).orElse(null);

        if(administrator == null) {
            throw new UserNotFoundException(String.format("user with id %d not found, request will be ignored", chatModel.getAdministratorId()));
        }

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(chatModel.getId());
        chatEntity.setTitle(chatModel.getTitle());
        chatEntity.setAdministrator(administrator);
        chatRepository.save(chatEntity);

        return chatModel;
    }
}
