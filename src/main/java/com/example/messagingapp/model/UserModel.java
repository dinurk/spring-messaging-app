package com.example.messagingapp.model;


import com.example.messagingapp.entity.UserEntity;

public class UserModel {
    private Long id;
    private String email;

    public static UserModel toUserModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setEmail(userEntity.getEmail());
        userModel.setId(userEntity.getId());

        return userModel;
    }

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
