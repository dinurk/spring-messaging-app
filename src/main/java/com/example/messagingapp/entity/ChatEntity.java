package com.example.messagingapp.entity;

import jakarta.persistence.*;

@Entity
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private UserEntity administrator;

    public ChatEntity() {
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

    public UserEntity getAdministrator() {
        return administrator;
    }

    public void setAdministrator(UserEntity administrator) {
        this.administrator = administrator;
    }
}
