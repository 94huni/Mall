package com.springboot.Mall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;

    @Column
    private String userId;

    @Column
    private String nickname;

    @Column
    private String pw;

    @Column
    private String date;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Product> product;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comment;
}
