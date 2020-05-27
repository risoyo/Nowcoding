package com.nowcoder.community.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(value = "LoginRequest", description = "用户登录请求参数")
public class LoginRequest {
    @ApiModelProperty(value = "用户名(4-50个字符)", required = true, position = 1)
    @NotBlank
    private String name;

    @NotBlank
    private String pass;
}
