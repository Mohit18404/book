package com.book.book.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrdersPerMonth {
    private String monthName;
    private int orderCount;
    private double totalPurchasedAmount;
    private int totalBookCount;
}
