package com.book.book.requests;

import lombok.Data;

@Data
public class OrderRequest {

    private int bookId;
    private int userId;
    private int itemCount;

}
