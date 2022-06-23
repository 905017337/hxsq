package com.jm.cloud.service.impl;

import com.jm.cloud.entity.UserPhone;
import com.jm.cloud.mapper.UserPhoneMapper;
import com.jm.cloud.service.IUserPhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 手机号用户表 服务实现类
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Service
public class UserPhoneServiceImpl extends ServiceImpl<UserPhoneMapper, UserPhone> implements IUserPhoneService {

}
