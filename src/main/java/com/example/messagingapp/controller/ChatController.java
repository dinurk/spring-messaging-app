package com.example.messagingapp.controller;

import com.example.messagingapp.dto.CreateChatDTO;
import com.example.messagingapp.entity.ChatEntity;
import com.example.messagingapp.service.ChatService;
import com.example.messagingapp.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateChatDTO createChatDTO) {
        try {
            ChatEntity chatEntity = chatService.create(createChatDTO);
            return ResponseEntity.created(
                            ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(chatEntity.getId())
                                    .toUri())
                    .body(createChatDTO);
        }
        catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
