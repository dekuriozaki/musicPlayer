package com.manikandan.musicPlayer.model;

import lombok.Data;

@Data
public class CreateTransactionRecordRequest {
    private String userId;
    private Character transactionType;
    private Double transactionAmount;
    private String transactionCategory;
    private String transactionDetail;
}