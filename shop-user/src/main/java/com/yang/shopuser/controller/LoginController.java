package com.yang.shopuser.controller;

import com.yang.shopuser.VO.ResultVO;
import com.yang.shopuser.constant.CookieConstant;
import com.yang.shopuser.constant.RedisConstant;
import com.yang.shopuser.entity.UserInfo;
import com.yang.shopuser.enums.ResultEnum;
import com.yang.shopuser.enums.RoleEnum;
import com.yang.shopuser.server.UserInfoServer;
import com.yang.shopuser.util.CookieUtils;
import com.yang.shopuser.util.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:20
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserInfoServer userInfoServer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/login/buyer")
    public ResultVO buyerLogin(@RequestParam("openid") String openid,
                               HttpServletResponse response){
        // 1. 与数据库交互判断
        UserInfo userInfo = userInfoServer.findUserByOpenid(openid);
        if(userInfo == null){
            return ResultVoUtils.error(ResultEnum.LOGIN_FAIL);
        }
        // 2. 角色判断
        if(RoleEnum.BUYER.getCode() != userInfo.getRole()){
            return ResultVoUtils.error(ResultEnum.ROLE_FAIL);
        }
        // 3. 写入cookie
        CookieUtils.set(response, CookieConstant.OPENID,openid,CookieConstant.EXPIRE);
        return ResultVoUtils.success();
    }

    @GetMapping("/login/seller")
    public ResultVO sellerLogin(@RequestParam("openid") String openid,
                                HttpServletRequest request,
                                HttpServletResponse response){
        //判断用户是否已登录，更新cookie和redis国企时间
        Cookie cookie = CookieUtils.get(request,CookieConstant.TOKEN);
        if(cookie != null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
            return ResultVoUtils.success();
        }

        // 1. 与数据库交互判断
        UserInfo userInfo = userInfoServer.findUserByOpenid(openid);

        if(userInfo == null){
            return ResultVoUtils.error(ResultEnum.LOGIN_FAIL);
        }
        // 2. 角色判断
        if(RoleEnum.SALLER.getCode() != userInfo.getRole()){
            return ResultVoUtils.error(ResultEnum.ROLE_FAIL);
        }

        // 3. 存入redis,若存储报错则不往cookie写了，因为读取请求cookie时，是先读cookie
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                openid,
                CookieConstant.EXPIRE,
                TimeUnit.SECONDS
        );

        // 4. 写入cookie
        CookieUtils.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return ResultVoUtils.success();
    }
}
