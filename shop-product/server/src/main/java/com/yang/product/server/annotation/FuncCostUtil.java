package com.yang.product.server.annotation;

import java.lang.annotation.*;

/**
 * @author yby
 * @version 1.0
 * @date 2020/10/21 16:28
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FuncCostUtil {
    String value() default "";
}
