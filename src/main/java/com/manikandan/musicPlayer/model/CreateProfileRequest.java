package com.manikandan.musicPlayer.model;

import lombok.Data;

@Data
public class CreateProfileRequest {
    private String userId;
    private Double currentBalance;
}
