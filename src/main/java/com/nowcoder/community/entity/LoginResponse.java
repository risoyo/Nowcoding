package com.nowcoder.community.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginResponse {
    @NotBlank
    private String headerUrl;

    @NotBlank
    private String token;
}
