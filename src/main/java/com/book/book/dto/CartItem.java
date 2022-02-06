package com.book.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "item_count")
    private int itemCount;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "cart_id")
    private int cartId;

}
