package com.book.book.requests;

import lombok.Data;

@Data
public class BookRequest {

    private int bookId;
    private String bookName;
    private String bookPublisher;
    private double price;
    private int availableCount;

}
