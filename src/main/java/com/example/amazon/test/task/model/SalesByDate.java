package com.example.amazon.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SalesByDate {
    private OrderedProductSales orderedProductSales;
    private OrderedProductSalesB2B orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private AverageSalesPerOrderItem averageSalesPerOrderItem;
    private AverageSalesPerOrderItem averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private AverageSellingPrice averageSellingPrice;
    private AverageSellingPrice averageSellingPriceB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private ClaimsAmount claimsAmount;
    private ShippedProductSales shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;
}
