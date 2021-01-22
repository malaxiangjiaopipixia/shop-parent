package com.yang.product.server.service.impl;

import com.yang.product.common.DecreaseStockInput;
import com.yang.product.common.ProductInfoOutput;
import com.yang.product.server.execption.ResultExecuption;
import com.yang.product.server.service.ProductInfoService;
import com.yang.product.server.entiry.ProductInfo;
import com.yang.product.server.enums.ProductInfoEnum;
import com.yang.product.server.enums.ResultEnum;
import com.yang.product.server.repository.ProductInfoRepository;
import com.yang.product.server.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 15:59
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductInfoEnum.UP.getCode());
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> listForOrder(List<String> list) {
        return productInfoRepository.findByProductIdIn(list);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputs) {
        List<ProductInfo> productInfos = decreaseStockProcess(decreaseStockInputs);
        List<ProductInfoOutput> productInfoOutputList = productInfos.stream().map(e->{
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e,output);
            return output;
        }).collect(Collectors.toList());
            //发送mq消息
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputs) {
        stringRedisTemplate.convertAndSend("cao","nidaye");




        List<ProductInfo> productInfos = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputs) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ResultExecuption(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer newStock = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (newStock < 0) {
                throw new ResultExecuption(ResultEnum.PRODUCT_STOCK_NOT_ENOUGH);
            }
            productInfo.setProductStock(newStock);
            productInfoRepository.save(productInfo);
            productInfos.add(productInfo);
        }
        return productInfos;
    }
}
