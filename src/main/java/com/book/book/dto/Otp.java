package com.book.book.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Otp")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @NotNull
    private String otp;
    @NotNull
    private Date createdAt;
    @NotNull
    private Date updatedAt;
    @NotNull
    private int retry;
    @NotNull
    private String status;
}
