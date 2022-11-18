package com.springboot.Mall.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String id;

    private String pw1;

    private String pw2;

    private String nickname;
}
