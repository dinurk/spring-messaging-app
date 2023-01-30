package com.example.messagingapp.dto;

import com.example.messagingapp.entity.ChatEntity;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CreateChatDTO {


    @Length(min = 4, max = 70)
    @NotNull
    private String title;

    @NotNull
    private Long administratorId;

    public CreateChatDTO() {
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
