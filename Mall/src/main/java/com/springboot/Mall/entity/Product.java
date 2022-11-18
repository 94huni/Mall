package com.springboot.Mall.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    @Column()
    private String name;

    @Column
    private String info;

    @Column
    private int price;

    @Column
    private int stock;

    private String FileImage;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private Cart cart;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<Comment> comment;
}
