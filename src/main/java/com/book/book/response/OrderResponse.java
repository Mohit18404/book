package com.book.book.response;

import com.book.book.dto.OrderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderResponse {

    private int code;
    private String description;
    private List<OrderData> listOfOrderData;
}
