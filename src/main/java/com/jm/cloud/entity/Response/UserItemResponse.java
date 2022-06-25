package com.jm.cloud.entity.Response;

import lombok.Data;

@Data
public class UserItemResponse {

    /** 性别 */
    private int gender;
    /**  */
    private String province;
    /**  */
    private String city;
    /** 头像 */
    private String avatarUrl;
    /** 微信名称 */
    private String nickName;
    /** 语言 */
    private String language;

}
