package com.jm.cloud.config;

/**
 * 配置文件 微信小程序常量配置
 */
public  class WxAppUrlConfig {

    /** appid */
    public static  final  String APPID="wx38deacbf93d0f5db";
    /** AppSecret */
    public static final String APPSECRET="5cd54db1fdac7370351f779f74b833da";

    //登录凭证校验
    public  static  final  String CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";

}
