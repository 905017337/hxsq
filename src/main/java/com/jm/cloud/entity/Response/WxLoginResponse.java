package com.jm.cloud.entity.Response;

import lombok.Data;

@Data
public class WxLoginResponse {
    private String code;
    private String token;
}
