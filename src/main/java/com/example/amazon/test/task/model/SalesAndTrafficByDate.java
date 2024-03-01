package com.example.amazon.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SalesAndTrafficByDate {
    private String date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;

}

