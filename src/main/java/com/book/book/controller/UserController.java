package com.book.book.controller;

import com.book.book.dto.User;
import com.book.book.enums.ResponseMessage;
import com.book.book.repository.UserRepository;
import com.book.book.requests.LoginRequest;
import com.book.book.requests.UserRequest;
import com.book.book.response.*;
import com.book.book.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("register")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserResponse userRegisterResponse = null;
        try {
            logger.info("[UserController][createUser] the request is userRequest: {}", userRequest);
            if (userService.validateUserRequest(userRequest)) {
                userRegisterResponse = userService.createUser(userRequest);
            } else {
                userRegisterResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            userRegisterResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return userRegisterResponse;
    }

    @GetMapping("{userId}/{purpose}/send/otp")
    public SendOtpResponse sendOtp(@PathVariable("userId") Integer userId, @PathVariable("purpose") String purpose, @RequestParam String loginId) {
        SendOtpResponse sendOtpResponse = null;
        try {
            logger.info("[UserController][sendOtp] the request is loginId: {}", loginId);
            if (loginId != null) {
                sendOtpResponse = userService.sendOtp(loginId);
            } else {
                sendOtpResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            sendOtpResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return sendOtpResponse;
    }

    @GetMapping("{userId}/{purpose}/validate/otp")
    public ValidateOtpResponse validateOtp(@PathVariable("userId") Integer userId, @PathVariable("purpose") String purpose, @RequestParam String loginId, @RequestParam String otp) {

        ValidateOtpResponse validateOtpResponse = null;
        try {
            logger.info("[UserController][validateOtp] the request is loginId: {}", loginId);
            if (loginId != null && otp != null) {
                validateOtpResponse = userService.validateOtp(loginId, otp);
            } else {
                validateOtpResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }
        } catch (Exception exception) {
            validateOtpResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return validateOtpResponse;
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = null;
        try {
            if (loginRequest != null && loginRequest.getLoginId() != null && loginRequest.getPassword() != null) {
                loginResponse = userService.validateUserIdAndPassword(loginRequest.getLoginId(), loginRequest.getPassword());
            } else {
                loginResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }
        } catch (Exception exception) {
            loginResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return loginResponse;
    }

    @GetMapping("logout/{userId}")
    public LogoutResponse logout(@PathVariable("userId") Integer userId, @RequestHeader("token") String token) {

        LogoutResponse logoutResponse = null;
        try {
            if (userId != null && token != null) {
                logoutResponse = userService.logout(userId, token);
            } else {
                logoutResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }
        } catch (Exception exception) {
            logoutResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return logoutResponse;
    }

    @GetMapping("{userId}")
    public UserDataResponse getUserUsingUserId(@PathVariable("userId") Integer userId) {

        UserDataResponse userResponse = new UserDataResponse();
        try {
            logger.info("[UserController][getUserUsingUserId] the request is userId: {}", userId);
            if (userId != null) {
                Optional<User> user = userRepository.findById(userId);
                if (user != null) {
                    userResponse = userResponse.builder().userId(user.get().getUserId()).firstName(user.get().getFirstName())
                            .lastName(user.get().getLastName()).email(user.get().getEmail()).phone(user.get().getPhone())
                            .code(HttpStatus.OK.value()).description(HttpStatus.OK.toString()).build();
                } else {
                    userResponse.builder().userId(userId).code(HttpStatus.OK.value()).description(ResponseMessage.INVALID_USER_ID.message).build();
                }
            } else {
                userResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.USER_NOT_FOUND.message).build();
            }
        } catch (Exception exception) {
            userResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return userResponse;
    }


}
