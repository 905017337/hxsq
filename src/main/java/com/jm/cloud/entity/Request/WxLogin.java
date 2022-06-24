package com.jm.cloud.entity.Request;

import lombok.Data;

@Data
public class WxLogin {
    private String code;
    private Boolean login;
}
