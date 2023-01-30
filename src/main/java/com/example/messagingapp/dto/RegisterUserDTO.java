package com.example.messagingapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class RegisterUserDTO {

    @NotNull
    @Length(min = 5, max = 100)
//    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\\\\\.[A-Za-z0-9-]+)*(\\\\\\\\.[A-Za-z]{2,})$")
    private String email;

    @NotNull
    @Length(min = 5, max = 100)
    private String name;

    @NotNull
    @Length(min = 8, max = 100)
    private String password;

    public RegisterUserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
