package com.manikandan.musicPlayer.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FetchTransactionChartRequest {
    private String userId;
    private String typeOfChart;
    private Timestamp fromDate;
    private Timestamp toDate;
}