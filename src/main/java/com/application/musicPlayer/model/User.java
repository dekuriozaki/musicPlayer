package com.application.musicPlayer.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public static final String SEQUENCE_NAME = "user_seq";

    @Id
    private String id;

    @NotBlank(message = "userName name must not be blank")
    private String userName;

    @NotBlank(message = "password name must not be blank")
    private String password;
 
    private boolean isDeleted;
}
