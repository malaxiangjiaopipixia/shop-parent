package com.yang.product.test;

/**
 * @author yby
 * @version 1.0
 * @date 2021/1/21 16:51
 */
@FunctionalInterface
public interface IConvert<F, T> {
    T convert(F f);
}
