package com.yang.shopuser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:06
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private int role;
}
