package com.book.book.services;

import com.book.book.dto.Book;
import com.book.book.enums.ResponseMessage;
import com.book.book.repository.BookRepository;
import com.book.book.requests.BookRequest;
import com.book.book.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        BookResponse bookResponse = new BookResponse();
        try {
            Book book = bookRepository.findByName(bookRequest.getBookName());
            if (book != null) {
                bookResponse.setCode(HttpStatus.CONFLICT.value());
                bookResponse.setDescription(ResponseMessage.BOOK_ALREADY_EXISTS.name());
            } else {
                book = mapRequestToBook(bookRequest);
                book.setCreatedAt(setCurrentDateAndTime());
                book = bookRepository.save(book);
                bookResponse = mapBookToResponse(book);
            }
        } catch (Exception e) {
            bookResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            bookResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }

        return bookResponse;
    }

    @Override
    public BookResponse updateBook(BookRequest bookRequest) {
        BookResponse bookResponse = new BookResponse();
        try {
            Book book = null;
            if (bookRequest.getBookId() != 0) {
                Optional<Book> book1 = bookRepository.findById(bookRequest.getBookId());
                book = book1.get();
            } else {
                book = bookRepository.findByName(bookRequest.getBookName());
            }
            if (book == null) {
                bookResponse.setCode(HttpStatus.NO_CONTENT.value());
                bookResponse.setDescription(ResponseMessage.BOOK_NOT_FOUND.name());
            } else {
                book = mapRequestToBookForUpdate(bookRequest, book);
                book = bookRepository.save(book);
                bookResponse = mapBookToResponse(book);
            }
        } catch (Exception e) {
            bookResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            bookResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }

        return bookResponse;
    }

    @Override
    public BookResponse getBook(int bookId) {
        BookResponse bookResponse = new BookResponse();
        try {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book == null || book.get() == null) {
                bookResponse.setCode(HttpStatus.NO_CONTENT.value());
                bookResponse.setDescription(ResponseMessage.BOOK_NOT_EXIST.name());
            } else {
                bookResponse = mapBookToResponse(book.get());
            }
        } catch (Exception e) {
            bookResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            bookResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        }

        return bookResponse;
    }

    private Book mapRequestToBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setBookId(bookRequest.getBookId());
        book.setBookName(bookRequest.getBookName());
        book.setBookPublisher(bookRequest.getBookPublisher());
        book.setAvailableCount(bookRequest.getAvailableCount());
        book.setPrice(bookRequest.getPrice());
        book.setUpdatedAt(setCurrentDateAndTime());
        return book;
    }

    private Book mapRequestToBookForUpdate(BookRequest bookRequest, Book book) {
        if (bookRequest.getBookName() != null) {
            book.setBookName(bookRequest.getBookName());
        }

        if (bookRequest.getBookPublisher() != null) {
            book.setBookPublisher(bookRequest.getBookPublisher());
        }

        if (bookRequest.getAvailableCount() != 0) {
            book.setAvailableCount(bookRequest.getAvailableCount());
        }

        if (bookRequest.getPrice() != 0.0) {
            book.setPrice(bookRequest.getPrice());
        }
        book.setUpdatedAt(setCurrentDateAndTime());
        return book;
    }

    private BookResponse mapBookToResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setCode(HttpStatus.OK.value());
        bookResponse.setDescription(ResponseMessage.BOOK_ADDED.name());
        bookResponse.setBookId(book.getBookId());
        bookResponse.setBookName(book.getBookName());
        bookResponse.setBookPublisher(book.getBookPublisher());
        bookResponse.setAvailableCount(book.getAvailableCount());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setCreationTime(book.getCreatedAt());
        bookResponse.setUpdatingTime(book.getUpdatedAt());
        return bookResponse;
    }


    private String setCurrentDateAndTime() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return format.format(today);
    }
}
