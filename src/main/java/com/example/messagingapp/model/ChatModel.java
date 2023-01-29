package com.example.messagingapp.model;

import com.example.messagingapp.entity.ChatEntity;

public class ChatModel {

    private Long id;
    private String title;
    private Long administratorId;

    public static ChatModel toChatModel(ChatEntity chatEntity) {
        ChatModel chatModel = new ChatModel();
        chatModel.setId(chatEntity.getId());
        chatModel.setTitle(chatEntity.getTitle());
        chatModel.setAdministratorId(chatEntity.getAdministrator().getId());
        return chatModel;
    }

    public ChatModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }
}
