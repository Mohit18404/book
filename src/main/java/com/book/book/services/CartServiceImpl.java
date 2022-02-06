package com.book.book.services;

import com.book.book.repository.CartRepository;
import com.book.book.requests.CartRequest;
import com.book.book.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartResponse addCart(CartRequest cartRequest) {
        CartResponse cartResponse = new CartResponse();
  /*      try {
            Cart cart = cartRepository.findByUserId(cartRequest.getUserId());
            if (cart != null) {
                List<Integer> listOfCartItems = cart.getListOfItems();
                if(listOfCartItems.contains(cartRequest.get))
                cartResponse.setCode(HttpStatus.CONFLICT.value());
                cartResponse.setDescription(ResponseMessage.BOOK_ALREADY_EXISTS.name());
            } else {
                cart = mapRequestToBook(bookRequest);
                cart.setCreatedAt(setCurrentDateAndTime());
                cart = bookRepository.save(book);
                bookResponse = mapBookToResponse(book);
            }
        } catch (Exception e) {
            bookResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            bookResponse.setDescription(ResponseMessage.INTERNAL_SERVER_ERROR.name());
            e.printStackTrace();
        } */

        return cartResponse;
    }

    @Override
    public CartResponse updateCart(CartRequest cartRequest) {
        return null;
    }

    @Override
    public CartResponse getCart(int bookId) {
        return null;
    }

    private String setCurrentDateAndTime() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return format.format(today);
    }
}
