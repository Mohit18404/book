package com.book.book.services;

import com.book.book.dto.Book;
import com.book.book.dto.OrderData;
import com.book.book.enums.ResponseMessage;
import com.book.book.repository.BookRepository;
import com.book.book.repository.OrderRepository;
import com.book.book.requests.OrderRequest;
import com.book.book.response.OrderResponse;
import com.book.book.response.OrdersPerMonth;
import com.book.book.response.StatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        OrderResponse orderResponse = new OrderResponse();
        try {
            Optional<Book> book = bookRepository.findById(orderRequest.getBookId());
            if (book != null && book.get() != null) {
                if (book.get().getAvailableCount() >= orderRequest.getItemCount()) {
                    OrderData orderData = mapRequestToOrder(orderRequest, book.get());
                    orderData.setOrderDate(setCurrentDateAndTime());
                    int availableCount = book.get().getAvailableCount() - orderRequest.getItemCount();
                    book.get().setAvailableCount(availableCount);
                    bookRepository.save(book.get());
                    orderData = orderRepository.save(orderData);
                    List<OrderData> orderDataList = new ArrayList<>();
                    orderDataList.add(orderData);
                    orderResponse = mapOrderToResponse(orderDataList);
                } else {
                    orderResponse.setCode(HttpStatus.OK.value());
                    orderResponse.setDescription(ResponseMessage.ORDER_OUT_OF_STOCK.name());
                }
            } else {
                orderResponse.setCode(HttpStatus.OK.value());
                orderResponse.setDescription(ResponseMessage.BOOK_NOT_EXIST.name());
            }
        } catch (Exception e) {
            orderResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            orderResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }
        return orderResponse;
    }

    private OrderData mapRequestToOrder(OrderRequest orderRequest, Book book) {
        OrderData orderData = new OrderData();
        orderData.setBookId(orderRequest.getBookId());
        orderData.setUserId(orderRequest.getUserId());
        orderData.setItemCount(orderRequest.getItemCount());
        orderData.setItemPrice(book.getPrice());
        orderData.setTotalPrice(orderRequest.getItemCount() * book.getPrice());
        orderData.setUpdatedAt(setCurrentDateAndTime());
        return orderData;
    }

    private OrderResponse mapOrderToResponse(List<OrderData> orderDataList) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setCode(HttpStatus.OK.value());
        orderResponse.setDescription(ResponseMessage.ORDER_CREATED.name());
        orderResponse.setListOfOrderData(orderDataList);
        return orderResponse;
    }


    @Override
    public OrderResponse getOrder(int orderId) {
        OrderResponse orderResponse = new OrderResponse();
        try {
            Optional<OrderData> order = orderRepository.findById(orderId);
            if (order == null || order.get() == null) {
                orderResponse.setCode(HttpStatus.NO_CONTENT.value());
                orderResponse.setDescription(ResponseMessage.ORDER_NOT_EXIST.name());
            } else {
                List<OrderData> orderDataList = new ArrayList<>();
                orderDataList.add(order.get());
                orderResponse = mapOrderToResponse(orderDataList);
            }
        } catch (Exception e) {
            orderResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            orderResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }

        return orderResponse;
    }

    @Override
    public OrderResponse orderFromDate(String startDate, String endDate) {
        OrderResponse orderResponse = new OrderResponse();
        try {
            List<OrderData> orderDataList = orderRepository.findByDateInterval(startDate, endDate);
            orderResponse = mapOrderToResponse(orderDataList);
        } catch (Exception e) {
            orderResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            orderResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }
        return orderResponse;
    }

    @Override
    public OrderResponse orderFromDateUsingUserId(int userid, String startDate, String endDate) {
        OrderResponse orderResponse = new OrderResponse();
        try {
            List<OrderData> orderDataList = orderRepository.findByDateIntervalForUserId(startDate, endDate, userid);
            orderResponse = mapOrderToResponse(orderDataList);
        } catch (Exception e) {
            orderResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            orderResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }
        return orderResponse;
    }

    @Override
    public StatisticsResponse getStatistics(int userId) {
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        try {
            List<OrderData> orderDataList = orderRepository.findOrdersUsingUserId(userId);
            statisticsResponse = createData(orderDataList, statisticsResponse, userId);
        } catch (Exception e) {
            statisticsResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            statisticsResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }
        return statisticsResponse;
    }

    private StatisticsResponse createData(List<OrderData> orderDataList, StatisticsResponse statisticsResponse, int userId) {
        statisticsResponse.setCode(HttpStatus.OK.value());
        statisticsResponse.setDescription(HttpStatus.OK.name());
        statisticsResponse.setUserId(userId);
        List<OrdersPerMonth> ordersPerMonthList = new ArrayList<>();

        Map<String, List<OrderData>> map = new HashMap<>();

        for (OrderData orderData : orderDataList) {
            String orderDate = orderData.getOrderDate();
            String month = stringToDate(orderDate);
            List<OrderData> orderData1 = null;
            if(map.containsKey(month)) {
                orderData1 = map.get(month);
            }else {
                orderData1 = new ArrayList<>();
            }
            orderData1.add(orderData);
            map.put(month, orderData1);
        }

        for (Map.Entry<String,List<OrderData>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<OrderData> value = entry.getValue();
            OrdersPerMonth ordersPerMonth = new OrdersPerMonth();
            ordersPerMonth.setMonthName(key);
            ordersPerMonth.setOrderCount(value.size());
            Integer bookCount = 0;
            double totalAmount = 0.0;
            for(OrderData orderTemp : value) {
                bookCount+=orderTemp.getItemCount();
                totalAmount+=orderTemp.getTotalPrice();
            }
            ordersPerMonth.setTotalBookCount(bookCount);
            ordersPerMonth.setTotalPurchasedAmount(totalAmount);
            ordersPerMonthList.add(ordersPerMonth);
        }
        statisticsResponse.setOrdersPerMonthList(ordersPerMonthList);
        return statisticsResponse;
    }

    private String  stringToDate(String date) {
        String monthName = null;

        try {
            DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date1 = (Date)dateFormater.parse(date);
            String[] strings = date1.toString().split(" ");
            monthName = strings[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthName;
    }

    private String setCurrentDateAndTime() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return format.format(today);
    }
}
