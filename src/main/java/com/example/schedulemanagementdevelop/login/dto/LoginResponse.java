package com.example.schedulemanagementdevelop.login.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final Long userId;
    private final String email;
    private final String userName;

    public LoginResponse(Long userId, String email, String userName) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
    }
}
