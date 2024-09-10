package com.application.musicPlayer.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.musicPlayer.model.User;
import com.application.musicPlayer.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public User getUser(String userName, String password) {
        Optional<User> userOptional =  Optional.ofNullable(userRepository.findByUserNameAndPasswordAndIsDeletedFalse(userName,password));
        if(userOptional.isPresent()){
            User entity = userOptional.get();
            return entity;
        }
        throw new NoSuchElementException("User not found for the credentials");
    }
}