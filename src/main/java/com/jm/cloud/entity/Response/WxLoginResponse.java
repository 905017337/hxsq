package com.jm.cloud.entity.Response;

import lombok.Data;

@Data
public class WxLoginResponse {
    private String token;
    private String avaurl;
    private String nickName;
}
