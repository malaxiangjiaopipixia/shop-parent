package com.yang.order.server.util;

import com.yang.order.server.enums.ExecptionEnum;
import com.yang.order.server.vo.ResultVO;

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
        resultVO.setMsg("success!");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(ExecptionEnum execptionEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(execptionEnum.getCode());
        resultVO.setMsg(execptionEnum.getMessage());
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success!");
        resultVO.setData(new Date());
        return resultVO;
    }
}
