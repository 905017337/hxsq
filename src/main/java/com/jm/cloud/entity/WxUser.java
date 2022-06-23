package com.jm.cloud.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 小程序-微信openid用户表
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appid;

    private String openid;

    private String unionid;

    private String sessionKey;

    private String accessToken;

    private Long userId;

    private Long createdAt;

    private Long updatedAt;


}
