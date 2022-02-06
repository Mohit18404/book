package com.book.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrderData")
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "item_price")
    private double itemPrice;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "item_count")
    private int itemCount;
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "updated_at")
    private String updatedAt;

}
