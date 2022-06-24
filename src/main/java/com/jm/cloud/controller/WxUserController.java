package com.jm.cloud.controller;


import com.jm.cloud.entity.Request.WxLogin;
import com.jm.cloud.service.IWxUserService;
import com.jm.cloud.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 小程序-微信openid用户表 前端控制器
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Slf4j
@RestController
@RequestMapping("/wx-user")
public class WxUserController {

    @Autowired
    private IWxUserService userService;

    @PostMapping("/token")
    public Result token(@RequestBody WxLogin wxLogin){
       return  userService.token(wxLogin.getCode());
    }
}
