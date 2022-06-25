package com.jm.cloud.entity.Request;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author caozhenhao
 * @since 2022-03-18
 */
@Data
public class TWxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    /**
     * 小程序用户openid
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别   0 男  1  女  2 人妖
     */
    private Integer gender;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    private String language;

    /**
     * 创建时间
     */
    private Integer ctime;

    /**
     * 手机类型
     */
    private String mobile;

    /**
     * 手机号码
     */
    private String telnum;


}
