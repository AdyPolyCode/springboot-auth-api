package com.poly.schoolDataManager.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreatePayload {
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 4, max = 12, message = "Username length must be between 4 and 12 included")
    private String username;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, max = 16, message = "Password length must be between 8 and 16 included")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
