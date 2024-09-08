package com.manikandan.musicPlayer.model;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String userId;
    private String password;
    private Long phoneNumber;
}
