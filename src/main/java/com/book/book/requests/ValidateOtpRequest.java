package com.book.book.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateOtpRequest {
    private String loginId;
    private int otp;
}
