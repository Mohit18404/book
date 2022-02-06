package com.book.book.controller;

import com.book.book.enums.ResponseMessage;
import com.book.book.requests.OrderRequest;
import com.book.book.response.OrderResponse;
import com.book.book.response.StatisticsResponse;
import com.book.book.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("create")
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = null;
        try {
            logger.info("[OrderController][createOrder] the request is orderRequest: {}", orderRequest);
            if (orderRequest != null) {
                orderResponse = orderService.createOrder(orderRequest);
            } else {
                orderResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            orderResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return orderResponse;
    }

    @GetMapping("get")
    public OrderResponse getOrder(@RequestParam Integer orderId) {
        OrderResponse orderResponse = null;
        try {
            logger.info("[OrderController][getOrder] the request is orderId: {}", orderId);
            if (orderId != null) {
                orderResponse = orderService.getOrder(orderId);
            } else {
                orderResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            orderResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return orderResponse;
    }

    @GetMapping("order/date")
    public OrderResponse getOrderListUsingDate(@RequestParam String startDate, @RequestParam String endDate) {
        OrderResponse orderResponse = null;
        try {
            logger.info("[OrderController][getOrder] the request is startDate: {} and endDate: {}", startDate, endDate);
            if (startDate != null && endDate != null) {
                orderResponse = orderService.orderFromDate(startDate, endDate);
            } else {
                orderResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            orderResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return orderResponse;
    }

    @GetMapping("statistics")
    public StatisticsResponse getStatistics(@RequestParam Integer userId) {
        StatisticsResponse statisticsResponse = null;
        try {
            logger.info("[OrderController][getStatistics] the request is userId: {}", userId);
            if (userId != null) {
                statisticsResponse = orderService.getStatistics(userId);
            } else {
                statisticsResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            statisticsResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return statisticsResponse;
    }

}
