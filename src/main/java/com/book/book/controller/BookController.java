package com.book.book.controller;

import com.book.book.enums.ResponseMessage;
import com.book.book.requests.BookRequest;
import com.book.book.response.BookResponse;
import com.book.book.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/book/")
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping("add")
    public BookResponse addBook(@RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = null;
        try {
            logger.info("[BookController][addBook] the request is bookRequest: {}", bookRequest);
            if (bookRequest != null) {
                bookResponse = bookService.addBook(bookRequest);
            } else {
                bookResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            bookResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return bookResponse;
    }

    @GetMapping("get")
    public BookResponse getBook(@RequestParam Integer bookId) {
        BookResponse bookResponse = null;
        try {
            logger.info("[BookController][getBook] the request is bookId: {}", bookId);
            if (bookId != null) {
                bookResponse = bookService.getBook(bookId);
            } else {
                bookResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            bookResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return bookResponse;
    }

    @PostMapping("update")
    public BookResponse updateBook(@RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = null;
        try {
            logger.info("[BookController][updateBook] the request is bookRequest: {}", bookRequest);
            if (bookRequest != null) {
                bookResponse = bookService.updateBook(bookRequest);
            } else {
                bookResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            bookResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return bookResponse;
    }
}
