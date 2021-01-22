package com.yang.shopuser.util;

import com.yang.shopuser.VO.ResultVO;
import com.yang.shopuser.enums.ResultEnum;

import java.util.Date;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:37
 */
public class ResultVoUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("success!");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("success!");
        resultVO.setData(new Date());
        return resultVO;
    }
}
