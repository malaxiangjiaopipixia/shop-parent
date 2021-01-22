package com.yang.product.server.controller;

import com.yang.product.common.DecreaseStockInput;
import com.yang.product.server.annotation.FuncCostUtil;
import com.yang.product.server.entiry.ProductCategory;
import com.yang.product.server.service.ProductInfoService;
import com.yang.product.server.vo.ResultVO;
import com.yang.product.server.entiry.ProductInfo;
import com.yang.product.server.service.ProductCategoryService;
import com.yang.product.server.vo.ProductInfoVO;
import com.yang.product.server.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/10 17:49
 */
@RestController
@RequestMapping("/product")
public class  ProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @FuncCostUtil("product.list")
    public ResultVO<ProductVO> list(){
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3. 从数据库查询类目
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(productVOList);
        return resultVO;
    }

    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> list){
        System.out.println(list);
        return productInfoService.listForOrder(list);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputs){

        productInfoService.decreaseStock(decreaseStockInputs);
    }
}
