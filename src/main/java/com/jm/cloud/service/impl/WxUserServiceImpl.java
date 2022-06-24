package com.jm.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jm.cloud.config.WxAppUrlConfig;
import com.jm.cloud.entity.Response.WxLoginResponse;
import com.jm.cloud.entity.WxUser;
import com.jm.cloud.mapper.WxUserMapper;
import com.jm.cloud.service.IWxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jm.cloud.utils.TokenUtils;
import com.jm.cloud.utils.result.Result;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 小程序-微信openid用户表 服务实现类
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Slf4j
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private WxUserMapper wxUserMapper;
    @Override
    public Result token(String code) {
        String url = WxAppUrlConfig.CODE2SESSION;
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", WxAppUrlConfig.APPID);
        map.put("secret",WxAppUrlConfig.APPSECRET);
        map.put("code",code);
        map.put("grant_type","authorization_code");
        String body = restTemplate.getForEntity(url,String.class,map).getBody();
        log.info("token: forEntity:[{}]", JSON.toJSONString(body));
        JSONObject jsonObject = JSON.parseObject(body);
        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        log.info("token: user login  openid:[{}] session_key:[{}]",openid,session_key);

        redisTemplate.opsForHash().put("userItem:session_key","openid:"+openid,session_key);
        //插入数据库 如果没有用户信息插入
        QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(WxUser::getOpenid,openid);
        WxUser tWxUser = wxUserMapper.selectOne(wrapper);
        if(StringUtils.isEmpty(tWxUser)){
            WxUser wxUser = new WxUser();
            wxUser.setOpenid(openid);
            wxUserMapper.insert(wxUser);
        }
//        String token = ijwtService.generateWxToken(openid);
        cn.hutool.json.JSONObject jsonObject1 = new cn.hutool.json.JSONObject();
        jsonObject1.putOnce("userId",openid);
        //超时时间小于0 设置默认的超时时间

        int expire = 1;
        //计算超时时间
//        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS).atStartOfDay(ZoneId.systemDefault());
//        Date expireDate = Date.from(zdt.toInstant());
        String token = TokenUtils.createToken(jsonObject1);
        redisTemplate.opsForHash().put("wxApp:token",openid,token);
//        QueryWrapper<TWxUser> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(TWxUser::getOpenid,openid);
//        TWxUser wxUser = wxAuthorityMapper.selectOne(queryWrapper);
//        WxUserItem userItem = new WxUserItem();
//        BeanUtil.copyProperties(wxUser,userItem);
        WxLoginResponse wxLogin = new WxLoginResponse();
        wxLogin.setToken(token);
        return Result.success(wxLogin);
    }
}
