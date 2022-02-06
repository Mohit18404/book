package com.book.book.services;

import com.book.book.requests.CartRequest;
import com.book.book.response.CartResponse;

public interface CartService {

    CartResponse addCart(CartRequest cartRequest);

    CartResponse updateCart(CartRequest cartRequest);

    CartResponse getCart(int cartId);

}
