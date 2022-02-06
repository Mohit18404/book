package com.book.book.enums;


public enum ResponseMessage {
    BAD_REQUEST("Invalid input. Please validate your inputs."),
    INTERNAL_SERVER_ERROR("Server has issues. Please try again."),
    CREATED("User successfully registered"),
    CONFLICT("User Already exist"),
    USER_NOT_FOUND("User not exist"),
    SEND_OTP("OTP send to user loginId"),
    OTP_NOT_MATCHED("OTP does not matched"),
    OTP_LIMIT_REACHED("OTP limit reached"),
    INVALID_USER_ID("Invalid ID supplied"),
    PASSWORD_NOT_MATCHED("Please enter correct password"),
    TOKEN_NOT_EXIST("Token is not exist for given userId"),
    BOOK_ALREADY_EXISTS("The given book is already exists"),
    BOOK_ADDED("The given book is added successfully"),
    BOOK_NOT_FOUND("The book you are trying to update is not found"),
    ORDER_CREATED("The order has been placed successfully"),
    ORDER_OUT_OF_STOCK("Sorry, The order is out of stock"),
    ORDER_NOT_EXIST("The given order is not exist for given order id"),
    BOOK_NOT_EXIST("The given book is not available at this time");

    public final String message;

    private ResponseMessage(String message) {
        this.message = message;
    }
}
