package com.book.book.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookResponse {

    private int code;
    private String description;
    private int bookId;
    private String bookName;
    private String bookPublisher;
    private double price;
    private int availableCount;
    private String creationTime;
    private String updatingTime;

}
