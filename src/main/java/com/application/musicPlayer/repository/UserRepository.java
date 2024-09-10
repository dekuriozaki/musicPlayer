package com.application.musicPlayer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.application.musicPlayer.model.User;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByUserNameAndPasswordAndIsDeletedFalse(String userName, String password);
}
