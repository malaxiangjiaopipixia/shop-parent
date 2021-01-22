package com.yang.product.server.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


/**
 * @author yby
 * @version 1.0
 * @date 2020/10/21 16:33
 */
@Aspect
@Component
public class FuncCostAspect {

    private static final Logger logger = LoggerFactory.getLogger(FuncCostAspect.class.getName());

    @Pointcut("@annotation(com.yang.product.server.annotation.FuncCostUtil)")
    private void pointcut() {
    }

    @Around("pointcut()")
    private Object around(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = pjp.proceed(pjp.getArgs());
        stopWatch.stop();

        long cost = stopWatch.getTotalTimeMillis();
        if (cost >= 0) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            String methodName = signature.toShortString();
            logger.info("Func: {}, cost: {}.", methodName, cost);
        }
        return obj;
    }
}
