package org.ice.core.controller;

import org.ice.app.vo.ResultVo;

public class BaseController {


    protected ResultVo successful(Object data) {
        ResultVo vo = new ResultVo();
        vo.setData(data);
        return vo;
    }

    protected ResultVo fail(String data) {
        ResultVo vo = new ResultVo();
        vo.setData(data);
        return vo;
    }

}
