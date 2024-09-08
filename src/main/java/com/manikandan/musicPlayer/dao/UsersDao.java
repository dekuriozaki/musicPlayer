package com.manikandan.musicPlayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manikandan.musicPlayer.entity.Users;

import java.util.List;

@Repository
public interface UsersDao extends JpaRepository<Users,Integer> {

    List<Users> findByUserId(String userId);
    List<Users> findByUserIdAndPassword(String userId, String password);

}
