package com.book.book.repository;


import com.book.book.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT u FROM Book as u WHERE bookName = :bookName")
    Book findByName(@Param("bookName") String bookName);

}
