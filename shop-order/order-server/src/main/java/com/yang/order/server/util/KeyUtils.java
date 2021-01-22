package com.yang.order.server.util;

import java.util.Random;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 14:51
 */
public class KeyUtils {

    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
