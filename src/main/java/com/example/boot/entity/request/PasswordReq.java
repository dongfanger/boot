package com.example.boot.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordReq {
    @ApiModelProperty("旧密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;
}
