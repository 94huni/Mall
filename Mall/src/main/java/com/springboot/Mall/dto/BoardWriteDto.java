package com.springboot.Mall.dto;

import lombok.Data;

@Data
public class BoardWriteDto {
    private Long id;

    private String title;

    private String contents;
}
