package com.book.book.services;


import com.book.book.dto.User;
import com.book.book.requests.UserRequest;
import com.book.book.response.*;

public interface UserService {
    boolean validateUserRequest(UserRequest userRequest);

    UserResponse createUser(UserRequest userRequest);

    UserResponse checkUserExists(UserRequest userRequest);

    User getUserUsingPhone(String phone);

    User getUserUsingEmail(String email);

    UserResponse getUserUsingUserId(String userId);

    SendOtpResponse sendOtp(String loginId);

    ValidateOtpResponse validateOtp(String loginId, String otp);

    ValidateOtpResponse validateOtpFromDb(int userId, String otp);

    LoginResponse validateUserIdAndPassword(String loginId, String password);

    LogoutResponse logout(Integer userId, String token);
}
