package com.yang.shopuser.server;

import com.yang.shopuser.entity.UserInfo;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:16
 */
public interface UserInfoServer {

    UserInfo findUserByOpenid(String openid);
}
