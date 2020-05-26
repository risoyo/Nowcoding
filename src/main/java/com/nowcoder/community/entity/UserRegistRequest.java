package com.nowcoder.community.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRegistRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String pass;

    @NotBlank
    private String email;

    @NotBlank
    private int verifyCode;
}
