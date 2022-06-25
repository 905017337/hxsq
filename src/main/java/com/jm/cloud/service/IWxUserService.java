package com.jm.cloud.service;

import com.jm.cloud.entity.Request.WxAuthorization;
import com.jm.cloud.entity.WxUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jm.cloud.utils.result.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 小程序-微信openid用户表 服务类
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
public interface IWxUserService extends IService<WxUser> {

    Result token(String code);

    Object authorization(WxAuthorization wxAuthorization);

    Object info(HttpServletRequest request);
}
