package com.manikandan.musicPlayer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "transaction_sequence")
    private Long transactionSequence;

    @Column(name = "transaction_type")
    private Character transactionType;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "transaction_category")
    private String transactionCategory;

    @Column(name = "transaction_detail")
    private String transactionDetail;

    @Column(name = "transaction_time")
    private Timestamp transactionTime;

}
