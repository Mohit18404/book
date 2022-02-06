package com.book.book.repository;

import com.book.book.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User as u WHERE email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User as u WHERE phone = :phone")
    User findByPhone(@Param("phone") String phone);

}
