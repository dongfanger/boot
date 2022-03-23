package com.example.boot.entity.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResp {
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;
}
