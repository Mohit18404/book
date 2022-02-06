package com.book.book.requests;

import lombok.Data;

@Data
public class LogoutRequest {
    private String loginId;
    private String token;
}
