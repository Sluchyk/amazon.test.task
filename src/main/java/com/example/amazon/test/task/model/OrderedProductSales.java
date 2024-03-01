package com.example.amazon.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderedProductSales {
    private double amount;
    private String currencyCode;
}
