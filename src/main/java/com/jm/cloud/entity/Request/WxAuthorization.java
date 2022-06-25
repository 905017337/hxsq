package com.jm.cloud.entity.Request;

import lombok.Data;

@Data
public class WxAuthorization {

    /** 登录code */
    private String code;
    /**用户加密数据*/
    private String encryptedData;
    /**偏移量*/
    private String iv;
}
