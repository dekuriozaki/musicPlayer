package com.manikandan.musicPlayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manikandan.musicPlayer.entity.TransactionHistory;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionHistoryDao extends JpaRepository<TransactionHistory,Integer> {

    List<TransactionHistory> findByUserId(String userId);
    List<TransactionHistory> findByUserIdOrderByTransactionSequenceDesc(String userId);

    @Query("SELECT e FROM TransactionHistory e WHERE e.userId = :userId and e.transactionTime >= :fromDate AND e.transactionTime <= :toDate")
    List<TransactionHistory> findByUserIdWithinTheRange( @Param("userId") String userId,
                                                                @Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate);

    @Query("SELECT e FROM TransactionHistory e WHERE e.userId = :userId and e.transactionType = :transactionType and e.transactionTime >= :fromDate AND e.transactionTime <= :toDate")
    List<TransactionHistory> findByUserIdWithinTheRangeAndType( @Param("userId") String userId,
                                        @Param("transactionType") Character transactionType,
                                        @Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate);



}
