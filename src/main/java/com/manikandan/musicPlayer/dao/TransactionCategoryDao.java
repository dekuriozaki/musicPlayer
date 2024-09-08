package com.manikandan.musicPlayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manikandan.musicPlayer.entity.TransactionCategory;

@Repository
public interface TransactionCategoryDao extends JpaRepository<TransactionCategory,Integer> {

}
