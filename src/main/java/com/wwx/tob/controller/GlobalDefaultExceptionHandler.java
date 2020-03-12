package com.wwx.tob.controller;

import com.wwx.tob.dto.ResponseDto;
import com.wwx.tob.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: yinjiaolong
 * @Date: 2018/12/31 18:12
 * @Description:
 */

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {
    
    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public ResponseDto handleApplicationException(ApplicationException e) {
        log.error(e.getMessage(), e);
        return new ResponseDto().setErrMsg(e.getMessage()).setErrCode(e.getErrCode()).setApplicationError(true);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseDto handleException(Throwable e) {
        log.error(e.getMessage(), e);
        return new ResponseDto().setErrMsg(e.getMessage());
    }
}
