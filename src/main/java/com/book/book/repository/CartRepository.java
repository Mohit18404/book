package com.book.book.repository;

import com.book.book.dto.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT u FROM Cart as u WHERE userId = :userId")
    Cart findByUserId(@Param("userId") int userId);

}
