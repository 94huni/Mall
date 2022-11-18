package com.springboot.Mall.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private List<Product> product;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
