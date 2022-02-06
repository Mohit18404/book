package com.book.book.requests;

import lombok.Data;

@Data
public class LoginRequest {

    private String loginId;
    private String password;

}
