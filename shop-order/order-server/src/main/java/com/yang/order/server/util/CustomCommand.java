package com.yang.order.server.util;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author yby
 * @version 1.0
 * @date 2020/10/19 16:20
 *
 */
public class CustomCommand extends HystrixCommand {
    protected CustomCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected String run() throws Exception {
        return null;
    }
}
