package com.application.musicPlayer.controller;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.musicPlayer.model.User;
import com.application.musicPlayer.service.UserService;
import com.application.musicPlayer.utils.ApiResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/getUserByUserNameAndPassword")
    public ResponseEntity<ApiResponse<User>> getUser(@RequestParam String userName,@RequestParam String password ) {
        ApiResponse<User> response;
        try{
            User fetchedUser = userService.getUser(userName,password);
            response = new ApiResponse<>(fetchedUser, "User fetched successfully.");
            return ResponseEntity.ok(response);
        }catch (NoSuchElementException e) {
            response = new ApiResponse<>(null, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    
}
