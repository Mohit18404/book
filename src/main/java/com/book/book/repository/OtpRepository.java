package com.book.book.repository;


import com.book.book.dto.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

    @Query("SELECT u FROM Otp as u WHERE user_id = :userId")
    List<Otp> findByUserId(@Param("userId") Integer userId);

}

