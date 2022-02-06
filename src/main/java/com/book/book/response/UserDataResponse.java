package com.book.book.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDataResponse {
    private int code;
    private String description;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
