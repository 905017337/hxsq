package com.jm.cloud.service.impl;

import com.jm.cloud.entity.WxUser;
import com.jm.cloud.mapper.WxUserMapper;
import com.jm.cloud.service.IWxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小程序-微信openid用户表 服务实现类
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {

}
