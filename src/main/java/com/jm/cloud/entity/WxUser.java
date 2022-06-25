package com.jm.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

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

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String appid;

    private String openid;

    private String unionid;

    private String sessionKey;

    private String accessToken;

    private Integer userId;

    private Date createdTime;

    private Date updatedTime;


}
