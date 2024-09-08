package com.manikandan.musicPlayer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_profile")
public class UsersProfile {
    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int profileId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "current_balance")
    private Double currentBalance;

}
