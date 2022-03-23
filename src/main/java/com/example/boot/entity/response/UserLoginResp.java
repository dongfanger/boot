package com.example.boot.entity.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginResp {
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("用户信息")
    private UserResp user;
}
