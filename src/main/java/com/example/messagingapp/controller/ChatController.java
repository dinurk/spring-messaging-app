package com.example.messagingapp.controller;

import com.example.messagingapp.entity.ChatEntity;
import com.example.messagingapp.model.ChatModel;
import com.example.messagingapp.service.ChatService;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity create(@RequestBody ChatModel chat) {
        try {
            chatService.create(chat);
            return ResponseEntity.ok("new chat was created");
        }
        catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("an error occurred during new chat creation"+e.getStackTrace());
        }
    }
}
