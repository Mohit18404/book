package com.book.book.repository;

import com.book.book.dto.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, Integer> {

    @Query("SELECT u FROM OrderData as u WHERE orderDate BETWEEN :startDate AND :endDate")
    List<OrderData> findByDateInterval(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT u FROM OrderData as u WHERE userId = :userId")
    List<OrderData> findOrdersUsingUserId(@Param("userId") Integer idUser);

    @Query("SELECT u FROM OrderData as u WHERE userId = :userId AND (orderDate BETWEEN :startDate AND :endDate)")
    List<OrderData> findByDateIntervalForUserId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("userId") Integer userId);
}
