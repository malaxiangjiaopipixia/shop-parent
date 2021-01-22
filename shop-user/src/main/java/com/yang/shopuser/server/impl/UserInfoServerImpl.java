package com.yang.shopuser.server.impl;

import com.netflix.discovery.converters.Auto;
import com.yang.shopuser.entity.UserInfo;
import com.yang.shopuser.repository.UserInfoRepository;
import com.yang.shopuser.server.UserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:19
 */
@Service
public class UserInfoServerImpl implements UserInfoServer {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findUserByOpenid(String openid) {
        return userInfoRepository.findUserByOpenid(openid);
    }
}
