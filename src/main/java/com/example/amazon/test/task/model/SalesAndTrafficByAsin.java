package com.example.amazon.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SalesAndTrafficByAsin {
    private String parentAsin;
    private SalesByAsin salesByAsin;
    private TrafficByAsin trafficByAsin;
}


