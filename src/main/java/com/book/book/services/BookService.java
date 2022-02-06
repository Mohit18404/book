package com.book.book.services;

import com.book.book.requests.BookRequest;
import com.book.book.response.BookResponse;

public interface BookService {

    BookResponse addBook(BookRequest bookRequest);

    BookResponse updateBook(BookRequest bookRequest);

    BookResponse getBook(int bookId);



}
