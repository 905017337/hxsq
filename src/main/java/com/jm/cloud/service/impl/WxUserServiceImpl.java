package com.jm.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jm.cloud.config.WxAppUrlConfig;
import com.jm.cloud.constant.CommonConstant;
import com.jm.cloud.entity.Request.TWxUser;
import com.jm.cloud.entity.Request.WxAuthorization;
import com.jm.cloud.entity.Response.UserItemResponse;
import com.jm.cloud.entity.Response.WxLoginResponse;
import com.jm.cloud.entity.UserPhone;
import com.jm.cloud.entity.WxUser;
import com.jm.cloud.mapper.UserPhoneMapper;
import com.jm.cloud.mapper.WxUserMapper;
import com.jm.cloud.service.IWxUserService;
import com.jm.cloud.utils.TokenUtils;
import com.jm.cloud.utils.WxUserItemutils;
import com.jm.cloud.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private UserPhoneMapper userPhoneMapper;

    /**
     * 用户登录
     * @param code
     * @return
     */
    @Override
    public Result token(String code) {
        String url = getUrl();
        JSONObject jsonObject =jointConfiguration(code,url);

        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        redisTemplate.opsForHash().put("userItem:session_key","openid:"+openid,session_key);
        //插入数据库 如果没有用户信息插入
        QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(WxUser::getOpenid,openid);
        WxUser tWxUser = wxUserMapper.selectOne(wrapper);
        String avaurl = null;
        String nickName = null;
        if(StringUtils.isEmpty(tWxUser)){
            UserPhone userPhone1 = new UserPhone();
            HttpServletRequest request  = null;
            String JSONObject = restTemplate.getForEntity(CommonConstant.AVATAR,String.class).getBody();
            JSONObject jsonObjec = JSON.parseObject(JSONObject);
            String v1 = jsonObjec.get("code").toString();

            if("200".equals(v1)){
                String data = jsonObjec.get("data").toString();
                JSONObject jsonObject1 = JSON.parseObject(data);
                avaurl = jsonObject1.get("imgurl").toString();
            }
            userPhone1.setAvatar(avaurl);
            String nameJson = restTemplate.getForEntity(CommonConstant.NICK_NAME,String.class).getBody();
            JSONObject v2 = JSON.parseObject(nameJson);
            String data = v2.get("data").toString();
            JSONObject dataJson = JSON.parseObject(data);
            nickName = dataJson.get("name").toString();
            userPhone1.setNickname(nickName);
            userPhoneMapper.insert(userPhone1);
            WxUser wxUser = new WxUser();
            wxUser.setUserId(userPhone1.getId());
            wxUser.setOpenid(openid);
            wxUserMapper.insert(wxUser);
        }else {
            QueryWrapper<UserPhone> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserPhone::getId,tWxUser.getUserId());
            UserPhone userPhone = userPhoneMapper.selectOne(queryWrapper);
            avaurl = userPhone.getAvatar();
            nickName = userPhone.getNickname();
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
        wxLogin.setAvaurl(avaurl);
        wxLogin.setNickName(nickName);
        return Result.success(wxLogin);
    }

    /**
     * 授权获取个人信息
     * @param wxAuthorization
     * @return
     */
    @Override
    public Boolean authorization(WxAuthorization wxAuthorization) {
        String code = wxAuthorization.getCode();
        String url = getUrl();
        JSONObject jsonObject =jointConfiguration(code,url);
        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        JSONObject userInfo = WxUserItemutils.getUserInfo(wxAuthorization.getEncryptedData(),
                session_key, wxAuthorization.getIv());
        TWxUser user = JSON.toJavaObject(userInfo, TWxUser.class);
        UserPhone userItem = new UserPhone();
        userItem.setAvatar(user.getAvatarUrl());
        userItem.setNickname(user.getNickName());
        userItem.setUserIdentity(user.getGender());
        userPhoneMapper.insert(userItem);

        WxUser wxUser = new WxUser();
        wxUser.setUserId(userItem.getId());
        wxUser.setOpenid(openid);
        QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(WxUser::getOpenid,openid);
        wxUserMapper.update(wxUser,wrapper);
        return true;
    }

    @Override
    public Object info(HttpServletRequest request) {
        String token = request.getHeader(CommonConstant.JWT_USER_INFO_KEY);
        log.info("token: {}",token);
        String openid = TokenUtils.getUserIdByToken(token);
        log.info("openid: {}",openid);
        QueryWrapper<WxUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WxUser::getOpenid,openid);
        WxUser wxUser = wxUserMapper.selectOne(queryWrapper);
        UserItemResponse userItem = new UserItemResponse();
        QueryWrapper<UserPhone> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserPhone::getId,wxUser.getUserId());
        UserPhone userPhone = userPhoneMapper.selectOne(wrapper);
        if(null!=userPhone) {
            userItem.setAvatarUrl(userPhone.getAvatar());
            userItem.setNickName(userPhone.getNickname());
            userItem.setGender(userPhone.getUserIdentity());
        }
        return userItem;
    }


    private String getUrl(){
        return WxAppUrlConfig.CODE2SESSION;
    }

    /**
     * 获取用户信息
     * @param code
     * @param url
     * @return
     */
    private JSONObject jointConfiguration(String code,String url){
        HashMap<String, String> map = new HashMap<>();
        map.put("appid",WxAppUrlConfig.APPID);
        map.put("secret",WxAppUrlConfig.APPSECRET);
        map.put("code",code);
        map.put("grant_type","authorization_code");
        String body = restTemplate.getForEntity(url,String.class,map).getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }
}
