package com.springboot.Mall.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class UserModifyDto {

    private Long id;

    private String beforePw;

    private String afterPw;

    private String nickname;
}
