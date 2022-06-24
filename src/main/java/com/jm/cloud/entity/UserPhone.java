package com.jm.cloud.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * <p>
 * 手机号用户表
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPhone implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 手机号(不带区号)
     */
    private String phoneNumber;

    /**
     * 区号
     */
    private String countryCode;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像地址
     */
    private String avatar;

    private Integer showPrice;

    private Long createdAt;

    private Long updatedAt;

    /**
     * 用户身份
     */
    private Integer userIdentity;


}
