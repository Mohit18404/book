package com.book.book.controller;

import com.book.book.enums.ResponseMessage;
import com.book.book.requests.CartRequest;
import com.book.book.response.CartResponse;
import com.book.book.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/cart/")
public class CartController {
    @Autowired
    private CartService cartService;


    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping("add")
    public CartResponse addCart(@RequestBody CartRequest cartRequest) {
        CartResponse cartResponse = null;
        try {
            logger.info("[CartController][addCart] the request is cartRequest: {}", cartRequest);
            if (cartRequest != null) {
                cartResponse = cartService.addCart(cartRequest);
            } else {
                cartResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            cartResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return cartResponse;
    }

    @GetMapping("get")
    public CartResponse getCart(@RequestParam Integer cartId) {
        CartResponse cartResponse = null;
        try {
            logger.info("[CartController][getCart] the request is cartId: {}", cartId);
            if (cartId != null) {
                cartResponse = cartService.getCart(cartId);
            } else {
                cartResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            cartResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return cartResponse;
    }

    @PostMapping("update")
    public CartResponse updateCart(@RequestBody CartRequest cartRequest) {
        CartResponse cartResponse = null;
        try {
            logger.info("[CartController][updateCart] the request is cartRequest: {}", cartRequest);
            if (cartRequest != null) {
                cartResponse = cartService.updateCart(cartRequest);
            } else {
                cartResponse.builder().code(HttpStatus.BAD_REQUEST.value()).description(ResponseMessage.BAD_REQUEST.message).build();
            }

        } catch (Exception exception) {
            cartResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(ResponseMessage.INTERNAL_SERVER_ERROR.message).build();
        }

        return cartResponse;
    }
}
