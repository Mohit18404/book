package com.book.book.requests;

import lombok.Data;

@Data
public class CartRequest {

    private int userId;
    private int bookId;
    private int quantity;

}
