package com.yang.shopuser.repository;

import com.yang.shopuser.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:14
 */
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findUserByOpenid(String openid);
}
