package com.manikandan.musicPlayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manikandan.musicPlayer.entity.UsersProfile;

import java.util.List;

@Repository
public interface UsersProfileDao extends JpaRepository<UsersProfile,Integer> {

    List<UsersProfile> findByUserId(String userId);

}
