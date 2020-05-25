package com.nowcoder.community.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GetVerifyCodeRequest {
    @NotBlank
    private String email;
}
