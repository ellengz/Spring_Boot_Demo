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

    /**
     * capture and handle Exception which was thrown by the system
     * use ResultUtil to generate Result
     * @param e
     * @return Result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof StuffException) {
            StuffException stuffException = (StuffException) e;
            return ResultUtil.error(stuffException);
        } else {
            logger.error("[SYSTEM_ERROR] {}", e);
            StuffException stuffException = new StuffException(ResultEnum.UNKNOWN_ERROR);
            return ResultUtil.error(stuffException);
        }
    }
}
