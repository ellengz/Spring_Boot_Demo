package com.ellen.demo.handler;

import com.ellen.demo.domain.Result;
import com.ellen.demo.enums.ResultEnum;
import com.ellen.demo.exception.StuffException;
import com.ellen.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof StuffException) {
            StuffException stuffException = (StuffException) e;
            return ResultUtil.error(stuffException.getCode(),stuffException.getMessage());
        } else {
            logger.error("[SYSTEM_ERROR] {}", e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }
}
