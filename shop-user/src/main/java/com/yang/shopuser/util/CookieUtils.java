package com.yang.shopuser.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:57
 */
public class CookieUtils {

    public static void set(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge
    ){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie:cookies){
                if(cookie.getName().endsWith(name)){
                    return cookie;
                }
            }
        }
        return null;
    }
}
