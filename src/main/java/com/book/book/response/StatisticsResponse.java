package com.book.book.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StatisticsResponse {

    private int code;
    private String description;
    private int userId;
    private List<OrdersPerMonth> ordersPerMonthList;

}
