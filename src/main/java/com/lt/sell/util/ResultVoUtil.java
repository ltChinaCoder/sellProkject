package com.lt.sell.util;

import com.lt.sell.vo.ResultVo;

public class ResultVoUtil {
    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setMsg("成功");
        resultVo.setCode(0);
        return resultVo;
    }

    public static ResultVo fail() {
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg("失败");
        resultVo.setCode(-1);
        return resultVo;
    }
}
