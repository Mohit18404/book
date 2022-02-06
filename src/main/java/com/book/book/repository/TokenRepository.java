package com.book.book.repository;

import com.book.book.dto.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT u FROM Token as u WHERE user_id = :userId")
    List<Token> findByUserId(@Param("userId") int userId);

    @Query("SELECT u FROM Token as u WHERE token = :token")
    Token findByToken(@Param("token") int token);

    @Query("SELECT u FROM Token as u WHERE user_id = :userId AND status = :status")
    List<Token> findByActiveTokenByUserId(@Param("userId") int userId, @Param("status") String status);

}
