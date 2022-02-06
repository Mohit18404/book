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
public class CartResponse {

    private int code;
    private String description;
    private List<CartItemResponse> cartItemResponseList;
    private double totalPrice;
    private String creationTime;
    private String updatingTime;

}
