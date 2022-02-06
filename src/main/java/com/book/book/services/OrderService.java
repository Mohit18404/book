package com.book.book.services;

import com.book.book.requests.OrderRequest;
import com.book.book.response.OrderResponse;
import com.book.book.response.StatisticsResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse getOrder(int orderId);

    OrderResponse orderFromDate(String startDate, String endDate);

    OrderResponse orderFromDateUsingUserId(int userid, String startDate, String endDate);

    StatisticsResponse getStatistics(int userId);

}
